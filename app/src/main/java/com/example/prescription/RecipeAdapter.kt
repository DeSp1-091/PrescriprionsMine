package com.example.prescription

import android.content.Context
import android.service.controls.templates.ThumbnailTemplate
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.getSystemService
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Recipe>
) : BaseAdapter() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object{
        private val LABEL_COLORS = hashMapOf(
            "Low" to R.color.colorLow,
            "Medium" to R.color.colorMedium,
            "Hard" to R.color.colorHard,
            "Low-Hard" to R.color.colorLowHard
        )
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if(convertView == null){
            view = inflater.inflate(R.layout.list_item_recipe, parent, false)
            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.recipe_list_thumbnail) as ImageView
            holder.titleTextView = view.findViewById(R.id.recipe_list_title) as TextView
            holder.subtitleTextView = view.findViewById(R.id.recipe_list_subtitle) as TextView
            holder.detailTextView = view.findViewById(R.id.recipe_list_detail) as TextView

            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView


        val recipe = getItem(position) as Recipe

        titleTextView.text = recipe.title
        subtitleTextView.text = recipe.description
        detailTextView.text = recipe.label

        Picasso.get()
            .load(recipe.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(thumbnailImageView)

        val titleTypeFace = ResourcesCompat.getFont(context, R.font.minecraft)
        titleTextView.typeface = titleTypeFace

        val subtitleTypeFace = ResourcesCompat.getFont(context, R.font.minecraft)
        subtitleTextView.typeface = subtitleTypeFace

        val detailsTypeFace = ResourcesCompat.getFont(context, R.font.minecraft)
        detailTextView.typeface = detailsTypeFace
        detailTextView.setTextColor(
            ContextCompat.getColor(
                context, LABEL_COLORS[recipe.label] ?: R.color.colorPrimary
            )
        )

        return view
    }

    private class ViewHolder{
        lateinit var titleTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detailTextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}