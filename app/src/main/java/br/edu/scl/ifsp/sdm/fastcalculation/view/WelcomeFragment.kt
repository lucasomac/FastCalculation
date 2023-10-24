package br.edu.scl.ifsp.sdm.fastcalculation.view

//é uma sub activity praticamente

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import br.edu.scl.ifsp.sdm.fastcalculation.game.Extras.EXTRA_SETTINGS
import br.edu.scl.ifsp.sdm.fastcalculation.OnPlayGame
import br.edu.scl.ifsp.sdm.fastcalculation.R
import br.edu.scl.ifsp.sdm.fastcalculation.game.models.Settings
import br.edu.scl.ifsp.sdm.fastcalculation.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var fragmentWelcomeBinding: FragmentWelcomeBinding
    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            settings =
                BundleCompat.getParcelable(it, EXTRA_SETTINGS, Settings::class.java) ?: Settings()

        }
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        fragmentWelcomeBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        "${getString(R.string.welcome)},${settings.playerName}!".also {
            fragmentWelcomeBinding.welcomeTv.text = it
        }
        "Welcome, !${settings.playerName}".also { fragmentWelcomeBinding.welcomeTv.text = it }
        fragmentWelcomeBinding.playerBt.setOnClickListener {
            (context as OnPlayGame).onPlayGame()
        }
        return fragmentWelcomeBinding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(settings: Settings) = WelcomeFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_SETTINGS, settings)

            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.restartGameMi).isVisible = false
    }
}