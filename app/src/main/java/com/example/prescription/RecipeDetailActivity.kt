package com.example.prescription

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    companion object{
        const val EXTRA_TITLE = "title"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, recipe: Recipe): Intent {
            val detailIntent = Intent(context, RecipeDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, recipe.title)
            detailIntent.putExtra(EXTRA_URL, recipe.instructionUrl)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        var title = intent.extras?.getString(EXTRA_TITLE)
        var url = intent.extras?.getString(EXTRA_URL)
        setTitle(title)
        webView = findViewById(R.id.detail_web_view)
        webView.loadUrl(url.toString())
    }
}