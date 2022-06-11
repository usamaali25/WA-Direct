package com.example.wadirect


import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.wadirect.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customDialog:CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        customDialog = CustomDialog(this)
        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.bringToFront()
        binding.actionbar.menu.setOnClickListener {
            binding.drawerLayout.open()

        }
       /* val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            R.string.open,
            R.string.close

        )
        toggle.isDrawerIndicatorEnabled = false
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        toggle.toolbarNavigationClickListener = View.OnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }*/
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        with(viewPagerAdapter)
        {
            addFragment(HomeFragment())
            addFragment(HistoryFragment())
        }

        binding.viewPager.adapter = viewPagerAdapter
        binding.tablelayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem
                Objects.requireNonNull(tab.icon)
                    ?.setColorFilter(Color.parseColor("#25D366"), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                Objects.requireNonNull(tab.icon)!!
                    .setColorFilter(Color.parseColor("#FF000000"), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        binding.viewPager.isSaveEnabled = false
        TabLayoutMediator(binding.tablelayout, binding.viewPager) { tab, position ->
            val titles = arrayOf("Home", "History")
            if (position == 0) {
                tab.setIcon(R.drawable.ic_home)
            } else {
                tab.setIcon(R.drawable.ic_history)
            }
            tab.text = titles[position]
        }.attach()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        when (item.itemId) {
            R.id.rate -> {
                rateApp(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.rate ->
            {
                rateApp(this)
                Log.d("RATE1", "onNavigationItemSelected: Rate clicked")
            }
            R.id.share ->{
                shareIt(this)
            }
            R.id.other ->{

            }
            R.id.exit ->{
                myAlertDialog()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else myAlertDialog()

    }

    private fun myAlertDialog() {
        customDialog.setContentView(R.layout.dialog_layout)
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btn1: Button = customDialog.findViewById(R.id.rate)
        val btn2: Button = customDialog.findViewById(R.id.exit)
        btn1.setOnClickListener {
            rateApp(this)
        }
        btn2.setOnClickListener {
            customDialog.dismiss()
            finish()
        }
        customDialog.show()
    }

   /* override fun passData(messages: String, numbers: String) {
        val bundle = Bundle()
        bundle.putString("Message",messages)
        bundle.putString("Numbers",numbers)

        val transaction =  this.supportFragmentManager.beginTransaction()
        val historyFragment = HistoryFragment()

        historyFragment.arguments = bundle
        transaction.replace(R.id.drawer_layout,historyFragment)
    }*/


}