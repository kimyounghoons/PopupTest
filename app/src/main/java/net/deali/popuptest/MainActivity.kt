package net.deali.popuptest

import android.content.Intent
import android.os.Bundle
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LocalizationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvText.setOnClickListener {
            Intent(this, SecondActivity::class.java).let {
                startActivity(it)
            }
        }

        tvPopup.setOnClickListener {
            CommonAlertDialogFragment.showForNormal(fragmentManager = supportFragmentManager)
        }

    }
}