package com.team.bottles.feat.report.mvi

import com.team.bottles.core.common.UiSideEffect

sealed interface ReportSideEffect : UiSideEffect {

    data object NavigateToPingPong : ReportSideEffect

}