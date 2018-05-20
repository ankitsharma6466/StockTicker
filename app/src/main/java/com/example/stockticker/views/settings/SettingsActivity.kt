package com.example.stockticker.views.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.stockticker.R
import com.example.stockticker.common.BaseActivity
import com.example.stockticker.common.Constants
import com.example.stockticker.data.events.SymbolChangedEvent
import kotlinx.android.synthetic.main.activity_settings.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class SettingsActivity : BaseActivity() {

    @Inject
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var symbol = pref.getString(Constants.CURRENT_SYMBOL, Constants.DEFAULT_SYMBOL)

        symbolEditView.setText(symbol)
    }

    fun onSetDefaultClick(view: View) {

        var input = symbolEditView.text.toString()

        if(input.isNotBlank()) {

            pref.edit().putString(Constants.CURRENT_SYMBOL, input).apply()

            EventBus.getDefault().postSticky(SymbolChangedEvent(input))
            finish()

        } else {
            Toast.makeText(this, "Please provide a valid input", Toast.LENGTH_SHORT).show()
        }
    }
}
