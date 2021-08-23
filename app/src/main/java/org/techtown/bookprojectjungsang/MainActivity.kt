package org.techtown.bookprojectjungsang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.bookprojectjungsang.compose.NotesFragment
import org.techtown.bookprojectjungsang.databinding.ActivityMainBinding
import org.techtown.bookprojectjungsang.room.BookDatabase


class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

    //viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setInit()
    }

    private fun setInit() {
        //database setting
        bookDatabase = BookDatabase.getInstance(applicationContext)!!

        supportFragmentManager.beginTransaction().add(R.id.frame_frag,SearchFragment()).commitAllowingStateLoss()
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_frag, SearchFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.baggage -> {
                supportFragmentManager.beginTransaction().replace(R.id.frame_frag, NotesFragment()).commitAllowingStateLoss()
                return true
            }
        }

        return false
    }

    companion object{
        //database
        lateinit var bookDatabase: BookDatabase
    }
}