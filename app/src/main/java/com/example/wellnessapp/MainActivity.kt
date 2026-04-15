package com.example.wellnessapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AnimationUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        find buttons by use of their id
        val HealthButton = findViewById<Button>(R.id.health_recipes)
        val NutritionButton = findViewById<Button>(R.id.nutrition_advice)
        val MeditationButton = findViewById<Button>(R.id.meditation)
        val HydrationButton = findViewById<Button>(R.id.hydration_alert)
        val ExerciseButton = findViewById<Button>(R.id.start_exercise)
        val MotivationButton = findViewById<Button>(R.id.daily_motivation)
        val WeeklyGoalsButton = findViewById<Button>(R.id.weekly_goals)
        val ProgressButton = findViewById<Button>(R.id.check_progress)
        val LearnMoreButton = findViewById<Button>(R.id.learnmore)

        val anim = AnimationUtils.loadAnimation(this, R.anim.button_click)

//        set on click listener to  buttons as u do the intent to different activities
        HealthButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, HealthActivity::class.java)
            startActivity(intent)
        }
        //    ======================================================================
        NutritionButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, NutritionActivity::class.java)
            startActivity(intent)

        }

        //    ======================================================================
        MeditationButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, MeditationActivity::class.java)
            startActivity(intent)
        }
        //    ======================================================================
        HydrationButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, HydrationActivity::class.java)
            startActivity(intent)
        }
        //    ======================================================================
        ExerciseButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, ExerciseActivity::class.java)
            startActivity(intent)
            }
        //    ======================================================================
        MotivationButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, MotivationActivity::class.java)
            startActivity(intent)
        }
        //    ======================================================================
        WeeklyGoalsButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, WeeklyGoalsActivity::class.java)
            startActivity(intent)
        }
        //    ======================================================================
        ProgressButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(applicationContext, ProgressActivity::class.java)
            startActivity(intent)
        }

//        below is an implicit intent. When the button learn more is clicked, it takes us to the default browser

        LearnMoreButton.setOnClickListener {
            it.startAnimation(anim)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.healthline.com/health/how-to-maintain-a-healthy-lifestyle"))
            startActivity(intent)
        }

//        implementation of baner ads

        MobileAds.initialize(this)

        val adView = findViewById<AdView>(R.id.adView)

        val adRequest = AdRequest.Builder().build()

        adView.loadAd(adRequest)

        }

    fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        //Requests interstitial ads
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712", // Test ID
            adRequest,
            object : InterstitialAdLoadCallback() {

                override fun onAdLoaded(ad: InterstitialAd) {
                    mInterstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    mInterstitialAd = null
                }
            }
        )
    }
    //Function checks if ad already running not to run anothet one and overlap - which is wrong
    fun showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        }
    }
}
