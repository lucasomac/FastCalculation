package br.edu.scl.ifsp.sdm.fastcalculation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import br.edu.scl.ifsp.sdm.fastcalculation.OnPlayGame
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.FragmentScoreBoardBinding
import br.edu.scl.ifsp.sdm.fastcalculation.game.Extras
import br.edu.scl.ifsp.sdm.fastcalculation.game.models.ScoreBoardData

class ScoreBoardFragment : Fragment() {
    private lateinit var resultGameFragmentBinding: FragmentScoreBoardBinding

    private lateinit var scoreBoard: ScoreBoardData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            scoreBoard = BundleCompat.getParcelable(
                it, Extras.EXTRA_RESULT_GAME, ScoreBoardData::class.java
            ) ?: ScoreBoardData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        resultGameFragmentBinding =
            FragmentScoreBoardBinding.inflate(layoutInflater, container, false)

        resultGameFragmentBinding.apply {
            playAgainButton.setOnClickListener {
                (context as OnPlayGame).onPlayGame()
            }
            tvFinalScore.text = scoreBoard.points
        }

        return resultGameFragmentBinding.root
    }
}