package sharukh.piggy.mfcomparision.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import sharukh.piggy.mfcomparision.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sss.setOnClickListener {
            intent = Intent(this, SearchActivity::class.java)
            startActivityForResult(intent, 555)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {
            //SearchActivity
            555 -> {
                Snackbar.make(sss, data?.getStringExtra("MF_ID").toString(), Snackbar.LENGTH_SHORT).show()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)

    }
}
