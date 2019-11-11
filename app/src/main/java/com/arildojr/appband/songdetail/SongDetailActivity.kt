package com.arildojr.appband.songdetail

import android.os.Bundle
import android.util.Base64
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import com.arildojr.appband.R
import com.arildojr.appband.core.base.BaseActivity
import com.arildojr.appband.databinding.ActivitySongDetailBinding
import com.arildojr.appband.songlist.SongViewModel
import org.koin.android.ext.android.inject

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("bundle")) {
            val bundle = intent.getBundleExtra("bundle")
            binding.song = bundle?.getParcelable("song")
        }

        val html = """
<html><head>
<style type='text/css'>
b {
    color: #f09227; 
    font-weight: bold;
}
pre {
    white-space: pre-wrap; 
    word-break: break-word;
}
body {
    margin:0;
    padding:0;
    color: #000000; 
    font: 14px arial, sans-serif;
}
</style>
</head>
<body>
<section>
                <article itemprop="description">
            <pre>[Intro] <b>C</b>  <b>F9/C</b>  <b>Am7</b>  <b>G11</b>  <b>F9/C</b>

[Primeira Parte]

        <b>C</b>
Tem brinquedo espalhado pela casa toda
        <b>F9/C</b>
E as paredes rabiscadas com o giz de cera
          <b>Am7</b>        <b>G11</b>
Mudou de tal maneira
       <b>F9/C</b>
Nossa vida já não é a mesma

   <b>C</b>
A gente já não dorme mais a noite inteira noite inteira
    <b>F9/C</b>
Na mesa tem dois copos e uma mamadeira
          <b>Am7</b>        <b>G11</b>
Mudou de tal maneira
       <b>F9/C</b>
Nossa vida já não é a mesma

[Pré-Refrão]

 <b>Am7</b>
Tem um pinguinho de gente
       <b>D7</b>
Correndo na sala
 <b>F9/C</b>
Com o sorriso banguelo
                   <b>Fm7(13)</b>
Eu não quero mais nada

[Refrão]

 <b>C</b>
Sabe aquele amor que se multiplica?
  <b>F9/C</b>
Quem nunca sonhou ter isso na vida?
       <b>Am7</b>                 <b>G11</b>
Ser herói de alguém e, melhor ainda
        <b>F9/C</b>
Ter do lado a Mulher Maravilha

 <b>C</b>
Sabe aquele amor que se multiplica?
  <b>F9/C</b>
Quem nunca sonhou ter isso na vida?
       <b>Am7</b>                 <b>G11</b>
Ser herói de alguém e, melhor ainda
        <b>F9/C</b>
Ter do lado a Mulher Maravilha

( <b>C</b>  <b>F9/C</b>  <b>Am7</b>  <b>G11</b>  <b>F9/C</b> )

[Pré-Refrão]

 <b>Am7</b>
Tem um pinguinho de gente
       <b>D7</b>
Correndo na sala
 <b>F9/C</b>
Com o sorriso banguelo
                   <b>Fm7(13)</b>
Eu não quero mais nada

[Refrão]

 <b>C</b>
Sabe aquele amor que se multiplica?
  <b>F9/C</b>
Quem nunca sonhou ter isso na vida?
       <b>Am7</b>                 <b>G11</b>
Ser herói de alguém e, melhor ainda
        <b>F9/C</b>
Ter do lado a Mulher Maravilha

 <b>C</b>
Sabe aquele amor que se multiplica?
  <b>F9/C</b>
Quem nunca sonhou ter isso na vida?
       <b>Am7</b>                 <b>G11</b>
Ser herói de alguém e, melhor ainda
        <b>F9/C</b>                   <b>C</b>
Ter do lado a Mulher Maravilha</pre>
        </article>
    </section></body>"""


        val encodedHtml = Base64.encodeToString(html.toByteArray(), Base64.NO_PADDING)
        binding.webview.loadData(encodedHtml, "text/html", "base64")


    }

}
