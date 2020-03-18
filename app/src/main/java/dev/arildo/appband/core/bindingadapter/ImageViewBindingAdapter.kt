package dev.arildo.appband.core.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import dev.arildo.appband.R
import dev.arildo.appband.core.util.RoundedCornersTransformation
import dev.arildo.appband.core.util.convertDpToPx

@BindingAdapter(
    "bind:imageUrl",
    "bind:imageByteArray",
    "bind:cornerSize",
    requireAll = false
)
fun ImageView.imageFromUrl(
    imageUrl: String?,
    imageByteArray: ByteArray?,
    cornerSize: Int?
) {
    if (imageUrl != null || imageByteArray != null) {
        if (cornerSize != null) {
            imageUrl?.let { url ->
                loadRoundedImageView(
                    this,
                    url,
                    cornerSize,
                    RoundedCornersTransformation.CornerType.ALL
                )
            } ?: loadRoundedImageView(
                this,
                imageByteArray,
                cornerSize,
                RoundedCornersTransformation.CornerType.ALL
            )

        } else {
            imageUrl?.let { url ->
                loadImageView(this, url)
            } ?: loadImageView(this, imageByteArray)

        }
    }
}

fun <T> loadImageView(image: ImageView, imageUrl: T?) {
    Glide.with(image.context)
        .load(imageUrl)
        .placeholder(R.mipmap.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().dontTransform())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(image)
}

fun <T> loadRoundedImageView(
    image: ImageView,
    imageUrl: T?,
    round: Int,
    cornerType: RoundedCornersTransformation.CornerType?
) {
    Glide.with(image.context)
        .load(imageUrl)
        .placeholder(R.mipmap.placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().dontTransform())
        .transform(
            CenterCrop(), RoundedCornersTransformation(
                round.convertDpToPx(image.resources.displayMetrics).toFloat(),
                0f, cornerType
            )
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(image)
}