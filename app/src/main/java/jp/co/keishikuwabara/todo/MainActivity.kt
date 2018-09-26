package jp.co.keishikuwabara.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jp.co.keishikuwabara.todo.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val tx = supportFragmentManager.beginTransaction()
            tx.add(R.id.container, MainFragment(), MainFragment.TAG)
            tx.commit()
        }
    }
}
