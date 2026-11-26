package bo.edu.uajms.cristianalejandro.surmarket

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.botton_navigation)
        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){
                R.id.LoginFragment ->{
                    bottomNavigation.visibility = View.INVISIBLE

                }
                R.id.addProductFragment ->{
                    bottomNavigation.visibility = View.INVISIBLE
                }
                else ->{
                    bottomNavigation.visibility = View.VISIBLE
                }
            }
        }
    }
}