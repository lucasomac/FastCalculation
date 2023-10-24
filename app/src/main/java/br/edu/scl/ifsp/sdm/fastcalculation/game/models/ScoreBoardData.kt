package br.edu.scl.ifsp.sdm.fastcalculation.game.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScoreBoardData(
    val points: String = "0,0",
) : Parcelable