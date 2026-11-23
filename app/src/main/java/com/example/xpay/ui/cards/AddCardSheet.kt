package com.example.xpay.ui.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpay.R
import com.example.xpay.ui.theme.*
import com.example.xpay.ui.theme.XpayTheme
import kotlinx.coroutines.launch


data class NewCardInput(
    val brandId: String,      // matches CardEntity.brand, e.g. "MASTERCARD"
    val holderName: String,
    val last4: String,
    val expiryMonth: Int,     // 1..12
    val expiryYear: Int       // 4 digits, e.g. 2029
)

data class CardBrandOption(
    val id: String,
    val name: String,
    @param:DrawableRes val iconResId: Int
)

private val cardBrandOptions = listOf(
    CardBrandOption("MASTERCARD", "Mastercard", R.drawable.ic_mastercard2_foreground),
    CardBrandOption("VISA", "Visa", R.drawable.ic_visa_foreground),     // TODO: add the real Visa logo
    CardBrandOption("PAYPAL", "PayPal", R.drawable.ic_paypal_foreground), // TODO: add the real PayPal logo
    CardBrandOption("OTHER", "Other", R.drawable.ic_plus)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardSheet(
    onDismiss: () -> Unit = {},
    onAddNewCard: (NewCardInput) -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    // Animate the sheet down first, then tell the parent to remove it.
    val closeSheet: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismiss()
        }
    }

    // Form state. The number and CVV use plain `remember`, so sensitive data
    // is never written to the saved-instance bundle.
    var brandId by rememberSaveable { mutableStateOf(cardBrandOptions.first().id) }
    var holder by rememberSaveable { mutableStateOf("") }
    var number by remember { mutableStateOf("") }   // digits only
    var expiry by remember { mutableStateOf("") }   // "MMYY", digits only
    var cvv by remember { mutableStateOf("") }      // digits only

    val holderError = CardValidator.holderError(holder)
    val numberError = CardValidator.cardNumberError(number)
    val expiryError = CardValidator.expiryError(expiry)
    val cvvError = CardValidator.cvvError(cvv)
    val parsedExpiry = CardValidator.parseExpiry(expiry)

    val canSave = holderError == null && numberError == null &&
            expiryError == null && cvvError == null && parsedExpiry != null

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = SheetBackground,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 6.dp)
                    .width(36.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(FieldBorder)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()) // fields stay reachable when the keyboard is open
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp)
        ) {
            SheetHeader(onClose = closeSheet)

            Spacer(modifier = Modifier.height(16.dp))

            FieldLabel("Card Brand")
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cardBrandOptions.forEach { option ->
                    CardBrand(
                        brandName = option.name,
                        brandIcon = painterResource(option.iconResId),
                        isSelected = option.id == brandId,
                        onClick = { brandId = option.id },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CardInputField(
                label = "Card Holder Name",
                value = holder,
                onValueChange = { input ->
                    holder = input
                        .filter { it.isLetter() || it == ' ' }
                        .take(CardValidator.HOLDER_MAX_LENGTH)
                },
                placeholder = "Kelly Victor",
                error = holderError,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            CardInputField(
                label = "Card Number",
                value = number,
                onValueChange = { number = it.digitsOnly(CardValidator.CARD_NUMBER_LENGTH) },
                placeholder = "4532 8910 3412 2038",
                error = numberError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = CardNumberTransformation
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CardInputField(
                    label = "Expires Date",
                    value = expiry,
                    onValueChange = { expiry = it.digitsOnly(4) },
                    placeholder = "MM/YY",
                    error = expiryError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    visualTransformation = ExpiryTransformation,
                    modifier = Modifier.weight(1f)
                )

                CardInputField(
                    label = "CVV",
                    value = cvv,
                    onValueChange = { cvv = it.digitsOnly(4) },
                    placeholder = "•••",
                    error = cvvError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    parsedExpiry?.let { (month, year) ->
                        onAddNewCard(
                            NewCardInput(
                                brandId = brandId,
                                holderName = holder.trim(),
                                last4 = number.takeLast(4),
                                expiryMonth = month,
                                expiryYear = year
                            )
                        )
                        closeSheet()
                    }
                },
                enabled = canSave,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryButton,
                    contentColor = Color.White,
                    disabledContainerColor = PrimaryButton.copy(alpha = 0.35f),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "Save Card",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SheetHeader(onClose: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Add New Card",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Instantly issue & link to your account",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(FieldBorder)
                .clickable(onClickLabel = "Close", role = Role.Button, onClick = onClose),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun FieldLabel(text: String) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        color = LabelColor
    )
}

/**
 * A labelled text field with an inline error line.
 * The error is shown only after the user has left the field once,
 * so people are not told they are wrong while they are still typing.
 */
@Composable
fun CardInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var hadFocus by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    val visibleError = if (showError) error else null

    Column(modifier = modifier) {
        FieldLabel(label)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focus ->
                    if (hadFocus && !focus.isFocused) showError = true
                    hadFocus = focus.isFocused
                },
            placeholder = {
                Text(text = placeholder, color = PlaceholderColor, fontSize = 14.sp)
            },
            // Always present, so the layout does not jump when an error appears.
            supportingText = {
                Text(text = visibleError.orEmpty(), fontSize = 11.sp)
            },
            isError = visibleError != null,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                focusedBorderColor = FieldBorderFocused,
                unfocusedBorderColor = FieldBorder,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}

@Composable
fun CardBrand(
    brandName: String,
    brandIcon: Painter,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(24.dp)
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(shape)
            .background(Color.White)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color.Black else FieldBorder,
                shape = shape
            )
            .selectable(selected = isSelected, role = Role.RadioButton, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = brandIcon,
            contentDescription = brandName,
            tint = Color.Unspecified,
            modifier = Modifier.padding(10.dp)
        )
    }
}

// ---------------------------------------------------------------------------
// Input helpers
// ---------------------------------------------------------------------------

private fun String.digitsOnly(maxLength: Int): String = filter(Char::isDigit).take(maxLength)

/** Shows "4532891034122038" as "4532 8910 3412 2038". The stored value stays digits only. */
private object CardNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        val formatted = digits.chunked(4).joinToString(" ")
        val offsets = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                offset + (offset - 1).coerceAtLeast(0) / 4

            override fun transformedToOriginal(offset: Int): Int =
                (offset - offset / 5).coerceAtMost(digits.length)
        }
        return TransformedText(AnnotatedString(formatted), offsets)
    }
}

/** Shows "0529" as "05/29". The stored value stays "0529". */
private object ExpiryTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text
        val formatted = if (digits.length > 2) digits.take(2) + "/" + digits.drop(2) else digits
        val offsets = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                if (offset <= 2) offset else offset + 1

            override fun transformedToOriginal(offset: Int): Int =
                if (offset <= 2) offset else offset - 1
        }
        return TransformedText(AnnotatedString(formatted), offsets)
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardSheetPreview() {
    XpayTheme {
        AddCardSheet()
    }
}