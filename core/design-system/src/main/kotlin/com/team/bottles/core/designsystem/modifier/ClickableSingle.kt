package com.team.bottles.core.designsystem.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    interactionSource: MutableInteractionSource,
    indication: Indication?,
    onClick: () -> Unit
): Modifier = composed(
    inspectorInfo =
    debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["onClick"] = onClick
    }
) {
    val manager: SingleEventHandler = remember { DefaultSingleEventHandler() }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { manager.handle { onClick() } },
        role = role,
        indication = indication,
        interactionSource = remember { interactionSource }
    )
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.clickableSingleNoRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed(
    inspectorInfo =
    debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val manager: SingleEventHandler = remember { DefaultSingleEventHandler() }
    val interactionSource = remember { MutableInteractionSource() }

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { manager.handle { onClick() } },
        role = role,
        indication = null,
        interactionSource = interactionSource
    )
}

fun interface SingleEventHandler {
    fun handle(event: () -> Unit)
}

internal class DefaultSingleEventHandler : SingleEventHandler {
    private val currentTime: TimeMark get() = TimeSource.Monotonic.markNow()
    private val throttleDuration: Duration = 900.milliseconds
    private lateinit var lastEventTime: TimeMark

    override fun handle(event: () -> Unit) {
        if (::lastEventTime.isInitialized.not() || (lastEventTime + throttleDuration).hasPassedNow()) {
            event()
        }
        lastEventTime = currentTime
    }
}