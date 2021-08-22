package com.example.dagsterappkotlin.objects

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.dagsterappkotlin.R
import com.example.dagsterappkotlin.fragments.*
import com.example.dagsterappkotlin.utilits.APP_ACTIVITY
import com.example.dagsterappkotlin.utilits.replaceFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader
import com.mikepenz.materialdrawer.util.DrawerImageLoader

/* Обьект реализующий боковое меню Navigation Drawer */

class AppDrawer {

    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mCurrentProfile: ProfileDrawerItem

    private val fileSharedPreferences = "data"

    val sharedPreferences: SharedPreferences =
        APP_ACTIVITY.getSharedPreferences(fileSharedPreferences, Context.MODE_PRIVATE)

    fun create() {
        /* Создания бокового меню */
        initLoader()
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        /* Отключение выдвигающего меню */
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            APP_ACTIVITY.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        /* Включение выдвигающего меню */
        APP_ACTIVITY.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        APP_ACTIVITY.mToolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }

    private fun createDrawer() {
        /* Создание дравера */
        mDrawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(APP_ACTIVITY.mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Profile")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_person_24),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Add Product")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_work_outline_24),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Product List")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_format_list_bulleted_24),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Statistics")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_stairs_24),

                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(108)
                    .withIconTintingEnabled(true)
                    .withName("QR-code")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_qr_code_24),
                PrimaryDrawerItem().withIdentifier(109)
                    .withIconTintingEnabled(true)
                    .withName("About us")
                    .withSelectable(false)
                    .withIcon(R.drawable.ic_baseline_people_24)
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    clickToItem(position)
                    return false
                }
            }).build()

    }

    private fun clickToItem(position: Int) {
        when (position) {
            1 -> replaceFragment(ProfileFragment())
            2 -> replaceFragment(ProductFragment())
            3 -> replaceFragment(ProductsListFragment())
            4 -> replaceFragment(StatisticsFragment())

            5 -> replaceFragment(QRFragment())
            6 -> replaceFragment(AboutUsFragment())
        }
    }

    private fun createHeader() {
        /* Создание хедера*/
        mCurrentProfile = ProfileDrawerItem()
            .withName(sharedPreferences.getString("email", "email"))
            //.withEmail(8-988-145-79-27)
            //.withIcon(USER.photoUrl)
            //.withIdentifier(200)
        mHeader = AccountHeaderBuilder()
            .withActivity(APP_ACTIVITY)
            //.withHeaderBackground(R.drawable.header)
            .addProfiles(
                mCurrentProfile
            ).build()
    }

    fun updateHeader() {
        /* Обновления хедера */
        mCurrentProfile
            .withName("USER.fullname")
            .withEmail("USER.phone")
            .withIcon("USER.photoUrl")

        mHeader.updateProfile(mCurrentProfile)

    }

    private fun initLoader() {
        /* Инициализация лоадера для загрузки картинок в хедер */
        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
                //imageView.downloadAndSetImage(uri.toString())
            }
        })
    }
}