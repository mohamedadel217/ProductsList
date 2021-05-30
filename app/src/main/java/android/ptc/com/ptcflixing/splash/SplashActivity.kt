package android.ptc.com.ptcflixing.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ActivitySplashBinding
import android.ptc.com.ptcflixing.main.MainActivity
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animateLogo()
        navigateToHome()
    }

    fun animateLogo() {
        val backgroundImage: ImageView = this.findViewById(R.id.SplashScreenImage)


        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_side_animation)
        backgroundImage.startAnimation(slideAnimation)
    }

    fun navigateToHome() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}