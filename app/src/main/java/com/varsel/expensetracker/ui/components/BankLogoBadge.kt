package com.varsel.expensetracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BankLogoBadge(
    bankName: String,
    modifier: Modifier = Modifier,
    size: Dp = 36.dp
) {
    val normalized = bankName.uppercase()

    when {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
        // Indian Bank (Official Deep Blue + Golden/Yellow emblem)
        normalized.contains("INDIAN BANK") || normalized.contains("IDIB") || normalized.contains("IND BL") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF003B70)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color(0xFFFFB800),
                            modifier = Modifier.size(size * 0.44f)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "IB",
                                    color = Color(0xFF003B70),
                                    fontSize = (size.value * 0.20f).sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                }
            }
        }

        // HDFC Bank (Official Navy Blue + Red & White emblem)
<<<<<<< HEAD
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
        normalized.contains("HDFC") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF004C8F)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
<<<<<<< HEAD
<<<<<<< HEAD
                            .size(size * 0.72f)
                            .border(1.5.dp, Color(0xFFED1C24), RoundedCornerShape(3.dp)),
=======
                            .size(size * 0.75f)
                            .border(1.5.dp, Color(0xFFED1C24), RoundedCornerShape(4.dp)),
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                            .size(size * 0.72f)
                            .border(1.5.dp, Color(0xFFED1C24), RoundedCornerShape(3.dp)),
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "HDFC",
                            color = Color.White,
<<<<<<< HEAD
<<<<<<< HEAD
                            fontSize = (size.value * 0.20f).sp,
=======
                            fontSize = (size.value * 0.22f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                            fontSize = (size.value * 0.20f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // SBI (State Bank of India - Official Keyhole Blue circle)
        normalized.contains("SBI") || normalized.contains("STATE BANK") || normalized.contains("SBIN") -> {
            Surface(
                modifier = modifier.size(size),
                shape = CircleShape,
                color = Color(0xFF0066B3)
=======
        normalized.contains("SBI") || normalized.contains("STATE BANK") -> {
            Surface(
                modifier = modifier.size(size),
                shape = CircleShape,
                color = Color(0xFF1E5BB5)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // SBI (State Bank of India - Official Keyhole Blue circle)
        normalized.contains("SBI") || normalized.contains("STATE BANK") || normalized.contains("SBIN") -> {
            Surface(
                modifier = modifier.size(size),
                shape = CircleShape,
                color = Color(0xFF0066B3)
>>>>>>> f04611b (feat: add support for additional Indian banks)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
<<<<<<< HEAD
<<<<<<< HEAD
                    // SBI iconic circular keyhole emblem
                    Box(
                        modifier = Modifier
                            .size(size * 0.52f)
=======
                    // SBI iconic circular emblem with keyhole slot
                    Box(
                        modifier = Modifier
                            .size(size * 0.5f)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                    // SBI iconic circular keyhole emblem
                    Box(
                        modifier = Modifier
                            .size(size * 0.52f)
>>>>>>> f04611b (feat: add support for additional Indian banks)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
<<<<<<< HEAD
<<<<<<< HEAD
                                .size(size * 0.18f, size * 0.28f)
                                .background(Color(0xFF0066B3))
=======
                                .size(size * 0.22f, size * 0.28f)
                                .background(Color(0xFF1E5BB5))
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                                .size(size * 0.18f, size * 0.28f)
                                .background(Color(0xFF0066B3))
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        )
                    }
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // ICICI Bank (Official Maroon & Orange Arc)
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // ICICI Bank (Official Maroon & Orange Arc)
>>>>>>> f04611b (feat: add support for additional Indian banks)
        normalized.contains("ICICI") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFB32729)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
<<<<<<< HEAD
<<<<<<< HEAD
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
=======
                        horizontalArrangement = Arrangement.spacedBy(1.dp)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
>>>>>>> f04611b (feat: add support for additional Indian banks)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(size * 0.28f)
                                .clip(CircleShape)
                                .background(Color(0xFFF37021))
                        )
                        Text(
                            text = "i",
                            color = Color.White,
<<<<<<< HEAD
<<<<<<< HEAD
                            fontSize = (size.value * 0.44f).sp,
=======
                            fontSize = (size.value * 0.45f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                            fontSize = (size.value * 0.44f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // Axis Bank (Official Burgundy + Inverted A-pyramid)
        normalized.contains("AXIS") || normalized.contains("UTIB") -> {
=======
        normalized.contains("AXIS") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // Axis Bank (Official Burgundy + Inverted A-pyramid)
        normalized.contains("AXIS") || normalized.contains("UTIB") -> {
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF97144D)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "▲",
<<<<<<< HEAD
<<<<<<< HEAD
                        color = Color.White,
                        fontSize = (size.value * 0.38f).sp,
=======
                        color = Color(0xFFED1C24),
                        fontSize = (size.value * 0.35f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        color = Color.White,
                        fontSize = (size.value * 0.38f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // Kotak Mahindra Bank (Official Red + Infinity symbol)
        normalized.contains("KOTAK") || normalized.contains("KKBK") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFED1C24)
=======
        normalized.contains("KOTAK") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFEE1C25)
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // Kotak Mahindra Bank (Official Red + Infinity symbol)
        normalized.contains("KOTAK") || normalized.contains("KKBK") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFED1C24)
>>>>>>> f04611b (feat: add support for additional Indian banks)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "∞",
                        color = Color.White,
<<<<<<< HEAD
<<<<<<< HEAD
                        fontSize = (size.value * 0.50f).sp,
=======
                        fontSize = (size.value * 0.48f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        fontSize = (size.value * 0.50f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // Bank of Baroda (Baroda Vermillion Sun)
        normalized.contains("BARODA") || normalized.contains("BOB") || normalized.contains("BARB") -> {
=======
        normalized.contains("BARODA") || normalized.contains("BOB") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // Bank of Baroda (Baroda Vermillion Sun)
        normalized.contains("BARODA") || normalized.contains("BOB") || normalized.contains("BARB") -> {
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFF26522)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "BOB",
                        color = Color.White,
<<<<<<< HEAD
<<<<<<< HEAD
                        fontSize = (size.value * 0.24f).sp,
=======
                        fontSize = (size.value * 0.25f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        fontSize = (size.value * 0.24f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // Canara Bank (Official Blue + Yellow interlocking triangles)
        normalized.contains("CANARA") || normalized.contains("CNRB") -> {
=======
        normalized.contains("CANARA") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // Canara Bank (Official Blue + Yellow interlocking triangles)
        normalized.contains("CANARA") || normalized.contains("CNRB") -> {
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF0084C9)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "▲",
                        color = Color(0xFFFFD200),
<<<<<<< HEAD
<<<<<<< HEAD
                        fontSize = (size.value * 0.38f).sp,
=======
                        fontSize = (size.value * 0.35f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        fontSize = (size.value * 0.38f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD

        // Punjab National Bank (PNB Maroon & Gold)
        normalized.contains("PNB") || normalized.contains("PUNJAB") || normalized.contains("PUNB") -> {
=======
        normalized.contains("PNB") || normalized.contains("PUNJAB") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======

        // Punjab National Bank (PNB Maroon & Gold)
        normalized.contains("PNB") || normalized.contains("PUNJAB") || normalized.contains("PUNB") -> {
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFA20B27)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "PNB",
                        color = Color(0xFFFFC20E),
                        fontSize = (size.value * 0.24f).sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)

        // Union Bank of India (Union Red & Blue)
        normalized.contains("UNION") || normalized.contains("UBIN") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFD2232A)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "UBI",
                        color = Color.White,
                        fontSize = (size.value * 0.24f).sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // IDFC FIRST Bank (Official FIRST Maroon)
<<<<<<< HEAD
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
        normalized.contains("IDFC") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF9B1C26)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IDFC",
                        color = Color.White,
<<<<<<< HEAD
<<<<<<< HEAD
                        fontSize = (size.value * 0.23f).sp,
=======
                        fontSize = (size.value * 0.24f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        fontSize = (size.value * 0.23f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)

        // Indian Overseas Bank (IOB Blue & Gold)
        normalized.contains("IOB") || normalized.contains("IOBA") || normalized.contains("OVERSEAS") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF005A9C)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IOB",
                        color = Color(0xFFFFD700),
                        fontSize = (size.value * 0.24f).sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // Federal Bank (Federal Blue & Yellow)
        normalized.contains("FEDERAL") || normalized.contains("FDRL") -> {
<<<<<<< HEAD
=======
        normalized.contains("FEDERAL") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF003E7E)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "FED",
                        color = Color(0xFFFFC72C),
                        fontSize = (size.value * 0.25f).sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)

        // IndusInd Bank (Indus Crimson)
        normalized.contains("INDUSIND") || normalized.contains("INDB") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF861F41)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "INDB",
                        color = Color.White,
                        fontSize = (size.value * 0.21f).sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // Paytm Payments Bank
        normalized.contains("PAYTM") || normalized.contains("PYTM") -> {
<<<<<<< HEAD
=======
        normalized.contains("PAYTM") -> {
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF002E6E)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "paytm",
                        color = Color(0xFF00BAF2),
<<<<<<< HEAD
<<<<<<< HEAD
                        fontSize = (size.value * 0.20f).sp,
=======
                        fontSize = (size.value * 0.22f).sp,
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
                        fontSize = (size.value * 0.20f).sp,
>>>>>>> f04611b (feat: add support for additional Indian banks)
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)

        // Airtel Payments Bank
        normalized.contains("AIRTEL") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFED1C24)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "airtel",
                        color = Color.White,
                        fontSize = (size.value * 0.20f).sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Yes Bank
        normalized.contains("YES BANK") || normalized.contains("YESB") -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFF005BA6)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "YES",
                        color = Color(0xFFED1C24),
                        fontSize = (size.value * 0.24f).sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        // Fallback Bank Badge
<<<<<<< HEAD
=======
>>>>>>> 7470ac9 (feat(dashboard): overhaul dashboard UI and navigation)
=======
>>>>>>> f04611b (feat: add support for additional Indian banks)
        else -> {
            Surface(
                modifier = modifier.size(size),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalance,
                        contentDescription = bankName,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(size * 0.55f)
                    )
                }
            }
        }
    }
}
