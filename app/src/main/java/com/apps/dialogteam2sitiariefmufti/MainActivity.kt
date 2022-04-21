package com.apps.dialogteam2sitiariefmufti

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDialogStandar: Button = findViewById(R.id.btn_dialog_standar)
        val btnDialogAction: Button = findViewById(R.id.btn_dialog_action)
        val btnDialogCustom: Button = findViewById(R.id.btn_dialog_custom)
        val btnDialogFragment: Button = findViewById(R.id.btn_fragment_dialog)


        btnDialogStandar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Dialog Cancelable")
            dialog.setMessage("Dialog cancelable bisa dititup dengan klik bagian luar dialog")
            dialog.setCancelable(true)
            dialog.show()
        }

        btnDialogFragment.setOnClickListener {
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(supportFragmentManager, null)
        }

        btnDialogAction.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Dialog Dengan Button")
            dialog.setMessage("Dialog Dengan Button untuk berbagi Aksi")
            dialog.setIcon(R.mipmap.ic_launcher)

            dialog.setCancelable(false)
            dialog.setPositiveButton("Positive Button") { dialogInterface, p1 ->
                Toast.makeText(this, "Positive Button Clicked", Toast.LENGTH_LONG).show()
            }
            dialog.setNegativeButton("Negative Button") { dialogInterface, p1 ->
                Toast.makeText(this, "Negative Button Clicked", Toast.LENGTH_LONG).show()
            }
            dialog.setNeutralButton("Neutral Button") { dialogInterface, p1 ->
                Toast.makeText(this, "Neutral Button Clicked", Toast.LENGTH_LONG).show()
            }
            dialog.show()
        }

        btnDialogCustom.setOnClickListener {
            pembayaranLayoutDialog(R.layout.custom_dialog, R.color.purple_200)
        }
    }

    private fun pembayaranLayoutDialog(layoutId: Int, colorId: Int) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_ACTION_BAR)
        dialog.setContentView(layoutId)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {
            lp.copyFrom(dialog.window?.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val linearLayout = dialog.findViewById<LinearLayout>(R.id.customAlertTitleLayout)

            val okButton = dialog.findViewById<Button>(R.id.customAlertPositiveButton)

            val juta2 = dialog.findViewById<TextView>(R.id.tv_persen20)
            val juta5 = dialog.findViewById<TextView>(R.id.tv_persen50)
            val juta7 = dialog.findViewById<TextView>(R.id.tv_persen75)

            juta2.setOnClickListener {
                juta2.setBackgroundResource(R.drawable.persen_selected_round)
                juta5.setBackgroundResource(R.drawable.persen_non_round)
                juta7.setBackgroundResource(R.drawable.persen_non_round)
            }

            juta5.setOnClickListener {
                juta2.setBackgroundResource(R.drawable.persen_non_round)
                juta5.setBackgroundResource(R.drawable.persen_selected_round)
                juta7.setBackgroundResource(R.drawable.persen_non_round)
            }

            juta7.setOnClickListener {
                juta2.setBackgroundResource(R.drawable.persen_non_round)
                juta5.setBackgroundResource(R.drawable.persen_non_round)
                juta7.setBackgroundResource(R.drawable.persen_selected_round)
            }

            linearLayout.setBackgroundColor(ContextCompat.getColor(this, colorId))
            okButton.text = "OK"

            okButton.setBackgroundColor(ContextCompat.getColor(this, colorId))
            okButton.setOnClickListener {
                Toast.makeText( this,"Ok berhasil", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            dialog.window?.attributes = lp
            dialog.show()
        }
    }
}