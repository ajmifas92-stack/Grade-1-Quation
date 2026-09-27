package com.ifas.kidsquiz

import android.media.ToneGenerator
import android.media.AudioManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

private data class Question(val q: String, val options: List<String>, val answer: Int)
private data class Category(val title: String, val questions: List<Question>)

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var tone: ToneGenerator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        tone = ToneGenerator(AudioManager.STREAM_MUSIC, 80)
        setContent { KidsQuizApp(::speak, ::sound) }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale("ta", "IN")
            tts?.setSpeechRate(0.85f)
        }
    }

    private fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "kidsquiz")
    }

    private fun sound(correct: Boolean) {
        tone?.startTone(if (correct) ToneGenerator.TONE_PROP_ACK else ToneGenerator.TONE_PROP_NACK, if (correct) 180 else 280)
    }

    override fun onDestroy() {
        tts?.stop(); tts?.shutdown(); tone?.release(); super.onDestroy()
    }
}

private fun q(text: String, vararg options: String, answer: Int) = Question(text, options.toList(), answer)

private val categories = listOf(
    Category("பொருந்தாதது 🧩", listOf(
        q("இதில் பொருந்தாதது எது?", "யானை 🐘", "சிங்கம் 🦁", "கார் 🚗", "பூனை 🐱", answer=2),
        q("இதில் பொருந்தாதது எது?", "ஆப்பிள் 🍎", "ரொட்டி 🍞", "மாம்பழம் 🥭", "வாழை 🍌", answer=1),
        q("இதில் பொருந்தாதது எது?", "காக்கை 🐦", "கிளி 🦜", "நாய் 🐶", "புறா 🕊️", answer=2),
        q("இதில் பொருந்தாதது எது?", "புத்தகம் 📚", "பென்சில் ✏️", "ரப்பர் 🧹", "பந்து ⚽", answer=3),
        q("இதில் பொருந்தாதது எது?", "சட்டை 👕", "தொப்பி 🧢", "கரண்டி 🥄", "காற்சட்டை 👖", answer=2),
        q("இதில் பொருந்தாதது எது?", "மீன் 🐟", "நண்டு 🦀", "முயல் 🐰", "திமிங்கலம் 🐳", answer=2),
        q("இதில் பொருந்தாதது எது?", "சூரியன் ☀️", "நிலா 🌙", "நட்சத்திரம் ⭐️", "நாற்காலி 🪑", answer=3),
        q("இதில் பொருந்தாதது எது?", "பஸ் 🚌", "பைக் 🏍️", "கப்பல் 🚢", "சைக்கிள் 🚲", answer=2),
        q("இதில் பொருந்தாதது எது?", "கண் 👁️", "காது 👂", "மூக்கு 👃", "சட்டை 👕", answer=3),
        q("இதில் பொருந்தாதது எது?", "பால் 🥛", "தண்ணீர் 💧", "ஜூஸ் 🧃", "கல் 🪨", answer=3),
        q("இதில் பொருந்தாதது எது?", "ரோஜா 🌹", "மல்லிகை 🌼", "தாமரை 🪷", "மரம் 🪵", answer=3),
        q("இதில் பொருந்தாதது எது?", "ஆசிரியர் 🧑‍🏫", "மருத்துவர் 🧑‍⚕️", "காவலர் 👮", "டிவி 📺", answer=3),
        q("இதில் பொருந்தாதது எது?", "வட்டம் 🔴", "சதுரம் 🟦", "முக்கோணம் 🔺", "ஸ்பூன் 🥄", answer=3),
        q("இதில் பொருந்தாதது எது?", "நாய் 🐶", "பூனை 🐱", "ஆடு 🐐", "புலி 🐯", answer=3),
        q("இதில் பொருந்தாதது எது?", "ஷூ 👟", "சாக்க்ஸ் 🧦", "செருப்பு 🩴", "கண்ணாடி 👓", answer=3),
        q("இதில் பொருந்தாதது எது?", "தக்காளி 🍅", "உருளை 🥔", "கேரட் 🥕", "கேக் 🎂", answer=3),
        q("இதில் பொருந்தாதது எது?", "விமானம் ✈️", "ஹெலிகாப்டர் 🚁", "பறவை 🦅", "ரயில் 🚆", answer=3),
        q("இதில் பொருந்தாதது எது?", "பள்ளி 🏫", "வீடு 🏠", "மருத்துவமனை 🏥", "ஆப்பிள் 🍎", answer=3),
        q("இதில் பொருந்தாதது எது?", "பேனா 🖊️", "நோட்டு 📓", "சார்ப்னர் ✏️", "சோப் 🧼", answer=3),
        q("இதில் பொருந்தாதது எது?", "அக்கா 👧", "தம்பி 👦", "அம்மா 👩", "மேஜை 🪑", answer=3)
    )),
    Category("வரிசைப் புதிர் 🔴", listOf(
        q("🔴 🔵 🔴 🔵 ... அடுத்து என்ன வரும்?", "🔵 நீலம்", "🔴 சிவப்பு", "🟡 மஞ்சள்", "🟢 பச்சை", answer=1),
        q("🟡 🟢 🟡 🟢 ... அடுத்து என்ன வரும்?", "🟢 பச்சை", "🔴 சிவப்பு", "🟡 மஞ்சள்", "🔵 நீலம்", answer=2),
        q("🍎 🍌 🍎 🍌 ... அடுத்து என்ன வரும்?", "🍌 வாழைப்பழம்", "🍎 ஆப்பிள்", "🍇 திராட்சை", "🍊 ஆரஞ்சு", answer=1),
        q("🐶 🐱 🐶 🐱 ... அடுத்து என்ன வரும்?", "🐱 பூனை", "🐮 மாடு", "🐶 நாய்", "ஆடு 🐐", answer=2),
        q("⭐ 🌙 ⭐ 🌙 ... அடுத்து என்ன வரும்?", "⭐ நட்சத்திரம்", "🌙 நிலா", "☀️ சூரியன்", "☁️ மேகம்", answer=0),
        q("🔺 🟦 🔺 🟦 ... அடுத்து என்ன வரும்?", "🟦 சதுரம்", "🔺 முக்கோணம்", "🔴 வட்டம்", "⭐ நட்சத்திரம்", answer=1),
        q("🚗 🚌 🚗 🚌 ... அடுத்து என்ன வரும்?", "🚌 பஸ்", "🚲 சைக்கிள்", "🚗 கார்", "✈️ விமானம்", answer=2),
        q("⚽ 🏀 ⚽ 🏀 ... அடுத்து என்ன வரும்?", "🏀 கூடைப்பந்து", "⚽ பந்து", "🎾 டென்னிஸ்", "🏏 கிரிக்கெட்", answer=1),
        q("🌹 🌼 🌹 🌼 ... அடுத்து என்ன வரும்?", "🌹 ரோஜா", "🌼 மல்லிகை", "🪷 தாமரை", "🌻 சூரியகாந்தி", answer=0),
        q("🍦 🎂 🍦 🎂 ... அடுத்து என்ன வரும்?", "🎂 கேக்", "🍦 ஐஸ்கிரீம்", "🍬 மிட்டாய்", "🍫 சாக்லேட்", answer=1),
        q("🔴 🔴 🔵 🔴 🔴 ... அடுத்து என்ன வரும்?", "🔴 சிவப்பு", "🔵 நீலம்", "🟡 மஞ்சள்", "🟢 பச்சை", answer=1),
        q("🟢 🟢 🟡 🟢 🟢 ... அடுத்து என்ன வரும்?", "🟢 பச்சை", "🟡 மஞ்சள்", "🔴 சிவப்பு", "🔵 நீலம்", answer=1),
        q("1, 2, 1, 2 ... அடுத்து என்ன வரும்?", "2", "1", "3", "4", answer=1),
        q("5, 10, 5, 10 ... அடுத்து என்ன வரும்?", "10", "15", "5", "20", answer=2),
        q("A, B, A, B ... அடுத்து என்ன வரும்?", "B", "A", "C", "D", answer=1),
        q("☀️ 🌙 ☀️ 🌙 ... அடுத்து என்ன வரும்?", "☀️ சூரியன்", "🌙 நிலா", "⭐️ நட்சத்திரம்", "☁️ மேகம்", answer=0),
        q("🐟 🦀 🐟 🦀 ... அடுத்து என்ன வரும்?", "🦀 நண்டு", "🐟 மீன்", "ஆமை 🐢", "திமிங்கலம் 🐳", answer=1),
        q("✏️ 📚 ✏️ 📚 ... அடுத்து என்ன வரும்?", "📚 புத்தகம்", "பந்து ⚽", "✏️ பென்சில்", "ஷூ 👟", answer=2),
        q("👕 👖 👕 👖 ... அடுத்து என்ன வரும்?", "👖 காற்சட்டை", "👕 சட்டை", "தொப்பி 🧢", "ஷூ 👟", answer=1),
        q("🥛 🧃 🥛 🧃 ... அடுத்து என்ன வரும்?", "🥛 பால்", "🧃 ஜூஸ்", "தண்ணீர் 💧", "தேநீர் ☕", answer=0)
    )),
    Category("அளவுகள் & ஒப்பீடு 📏", listOf(
        q("யானை மற்றும் எறும்பில் எது பெரியது?", "எறும்பு 🐜", "யானை 🐘", answer=1),
        q("பேருந்து மற்றும் மிதிவண்டியில் எது வேகமானது?", "பேருந்து 🚌", "மிதிவண்டி 🚲", answer=0),
        q("சூரியன் மற்றும் நிலவில் எது பகலில் வரும்?", "நிலா 🌙", "சூரியன் ☀️", answer=1),
        q("மரத்திற்கு மேலே பறப்பது எது?", "பறவை 🦅", "மீன் 🐟", answer=0),
        q("நீரில் நீந்துவது எது?", "பூனை 🐱", "மீன் 🐟", answer=1),
        q("இதில் எது உயரமானது?", "ஒட்டகச்சிவிங்கி 🦒", "முயல் 🐰", answer=0),
        q("இதில் எது கனமானது?", "இலை 🍃", "கல் 🪨", answer=1),
        q("இதில் எது சூடானது?", "ஐஸ்கிரீம் 🍦", "தேநீர் ☕", answer=1),
        q("இதில் எது குளிர்ந்தது?", "தீ 🔥", "பனிக்கட்டி 🧊", answer=1),
        q("இரவில் வானத்தில் வெளிச்சம் தருவது எது?", "நிலா 🌙", "சூரியன் ☀️", answer=0),
        q("இதில் எது இனிப்பானது?", "மிளகாய் 🌶️", "சர்க்கரை 🍬", answer=1),
        q("இதில் எது காரமானது?", "மிளகாய் 🌶️", "ஆப்பிள் 🍎", answer=0),
        q("வட்ட வடிவில் இருப்பது எது?", "பந்து ⚽", "பெட்டி 📦", answer=0),
        q("சதுர வடிவில் இருப்பது எது?", "நாணயம் 🪙", "கேரம் போர்டு 🎛️", answer=1),
        q("இதில் எது மென்மையானது?", "மரக்கட்டை 🪵", "பஞ்சு ☁️", answer=1),
        q("இதில் எது கடினமானது?", "தலையணை 🛋️", "கல் 🪨", answer=1),
        q("இதில் எது மெதுவாகச் செல்லும்?", "முயல் 🐰", "ஆமை 🐢", answer=1),
        q("இதில் எது வேகமாக ஓடும்?", "ஆமை 🐢", "சிறுத்தை 🐆", answer=1),
        q("பகலில் வானத்தில் இருப்பது எது?", "நட்சத்திரம் ⭐️", "சூரியன் ☀️", answer=1),
        q("நம் தலைக்கு மேலே இருப்பது எது?", "தரை 🪵", "வானம் ☁️", answer=1)
    )),
    Category("எண்ணுவோம் 🔢", listOf(
        q("1 + 1 = எவ்வளவு?", "1", "2", "3", "4", answer=1), q("2 + 1 = எவ்வளவு?", "3", "2", "4", "5", answer=0),
        q("3 + 2 = எவ்வளவு?", "4", "6", "5", "3", answer=2), q("5 + 0 = எவ்வளவு?", "0", "5", "10", "6", answer=1),
        q("2 + 2 = எவ்வளவு?", "4", "3", "5", "2", answer=0), q("4 + 1 = எவ்வளவு?", "3", "4", "5", "6", answer=2),
        q("3 + 3 = எவ்வளவு?", "5", "6", "7", "4", answer=1), q("5 + 5 = எவ்வளவு?", "10", "9", "11", "5", answer=0),
        q("10 + 1 = எவ்வளவு?", "10", "12", "11", "9", answer=2), q("6 + 2 = எவ்வளவு?", "7", "8", "9", "6", answer=1),
        q("7 + 1 = எவ்வளவு?", "8", "7", "9", "6", answer=0), q("4 + 4 = எவ்வளவு?", "6", "7", "8", "9", answer=2),
        q("9 + 1 = எவ்வளவு?", "10", "9", "11", "8", answer=0), q("5 + 3 = எவ்வளவு?", "7", "8", "9", "6", answer=1),
        q("2 + 8 = எவ்வளவு?", "9", "10", "11", "8", answer=1), q("10 + 10 = எவ்வளவு?", "15", "20", "25", "10", answer=1),
        q("1 + 0 = எவ்வளவு?", "1", "0", "2", "10", answer=0), q("6 + 4 = எவ்வளவு?", "9", "10", "11", "12", answer=1),
        q("7 + 3 = எவ்வளவு?", "9", "10", "8", "11", answer=1), q("8 + 2 = எவ்வளவு?", "10", "9", "8", "11", answer=0)
    )),
    Category("விலங்குகள் 🐶", listOf(
        q("'மியாவு மியாவு' என்று கத்துவது எது?", "நாய் 🐶", "பூனை 🐱", "ஆடு 🐐", "மாடு 🐮", answer=1),
        q("'லொள் லொள்' என்று குறைப்பது எது?", "பூனை 🐱", "நாய் 🐶", "யானை 🐘", "கிளி 🦜", answer=1),
        q("நமக்கு பால் தருவது எது?", "புலி 🐯", "மாடு 🐮", "சிங்கம் 🦁", "கரடி 🐻", answer=1),
        q("காட்டின் ராஜா யார்?", "முயல் 🐰", "மான் 🦌", "சிங்கம் 🦁", "நரி 🦊", answer=2),
        q("நீண்ட மூக்கு கொண்ட விலங்கு எது?", "யானை 🐘", "ஆடு 🐐", "குதிரை 🐴", "ஒட்டகம் 🐪", answer=0),
        q("மரத்திற்கு மரம் தாவுவது எது?", "நாய் 🐶", "குரங்கு 🐒", "பூனை 🐱", "ஆமை 🐢", answer=1),
        q("நமது தேசிய பறவை எது?", "காக்கை 🐦", "கிளி 🦜", "மயில் 🦚", "கழுகு 🦅", answer=2),
        q("பச்சையாக இருக்கும் பறவை எது?", "கிளி 🦜", "காக்கை 🐦", "கொக்கு 🦩", "ஆந்தை 🦉", answer=0),
        q("இரவில் விழித்திருக்கும் பறவை எது?", "கோழி 🐔", "ஆந்தை 🦉", "வாத்து 🦆", "மயில் 🦚", answer=1),
        q("வேகமாக ஓடும் விலங்கு எது?", "ஆமை 🐢", "யானை 🐘", "சிறுத்தை 🐆", "கரடி 🐻", answer=2),
        q("சாதுவான விலங்கு எது?", "முயல் 🐰", "புலி 🐯", "சிங்கம் 🦁", "நரி 🦊", answer=0),
        q("நீண்ட கழுத்து கொண்ட விலங்கு எது?", "நாய் 🐶", "பூனை 🐱", "ஒட்டகச்சிவிங்கி 🦒", "குதிரை 🐴", answer=2),
        q("முட்டை இடுவது எது?", "கோழி 🐔", "நாய் 🐶", "பூனை 🐱", "ஆடு 🐐", answer=0),
        q("தண்ணீரிலும் நிலத்திலும் வாழும் உயிரினம் எது?", "மான் 🦌", "தவளை 🐸", "குதிரை 🐴", "ஆடு 🐐", answer=1),
        q("வலை பின்னும் பூச்சி எது?", "எறும்பு 🐜", "ஈ 🪰", "சிலந்தி 🕷️", "கொசு 🦟", answer=2),
        q("தேன் சேகரிப்பது எது?", "பட்டாம்பூச்சி 🦋", "தேனீ 🐝", "ஈ 🪰", "கொசு 🦟", answer=1),
        q("வண்ண இறக்கைகள் கொண்ட பூச்சி எது?", "பட்டாம்பூச்சி 🦋", "எறும்பு 🐜", "ஈ 🪰", "சிலந்தி 🕷️", answer=0),
        q("சுவரில் ஊர்ந்து செல்லும் உயிரினம் எது?", "நாய் 🐶", "பூனை 🐱", "பல்லி 🦎", "ஆடு 🐐", answer=2),
        q("வீட்டைப் பாதுகாக்கும் விலங்கு எது?", "முயல் 🐰", "நாய் 🐶", "நரி 🦊", "மான் 🦌", answer=1),
        q("சவாரி செய்ய பயன்படும் விலங்கு எது?", "பூனை 🐱", "நாய் 🐶", "குதிரை 🐴", "முயல் 🐰", answer=2)
    )),
    Category("எதிர்ச்சொல் 🔤", listOf(
        q("'இரவு' என்பதன் எதிர்ச்சொல் என்ன?", "காலை", "பகல்", "இருள்", "மாலை", answer=1),
        q("'பெரிய' என்பதன் எதிர்ச்சொல் என்ன?", "சிறிய", "நீளமான", "உயரமான", "அழகான", answer=0),
        q("'உள்ளே' என்பதன் எதிர்ச்சொல் என்ன?", "மேலே", "கீழே", "வெளியே", "பக்கத்தில்", answer=2),
        q("'மேலே' என்பதன் எதிர்ச்சொல் என்ன?", "உள்ளே", "கீழே", "வெளியே", "முன்னே", answer=1),
        q("'நன்மை' என்பதன் எதிர்ச்சொல் என்ன?", "தீமை", "உண்மை", "அன்பு", "அழகு", answer=0),
        q("'உண்மை' என்பதன் எதிர்ச்சொல் என்ன?", "கோபம்", "பொய்", "அன்பு", "சிரிப்பு", answer=1),
        q("'சிரிப்பு' என்பதன் எதிர்ச்சொல் என்ன?", "அழுகை", "கோபம்", "பயம்", "தூக்கம்", answer=0),
        q("'சுத்தம்' என்பதன் எதிர்ச்சொல் என்ன?", "அழகு", "வாசம்", "அசுத்தம்", "வெளிச்சம்", answer=2),
        q("'வெளிச்சம்' என்பதன் எதிர்ச்சொல் என்ன?", "பகல்", "இருள்", "சூரியன்", "நிலா", answer=1),
        q("'வெப்பம்' என்பதன் எதிர்ச்சொல் என்ன?", "குளிர்", "தீ", "சூடு", "காற்று", answer=0),
        q("'வேகமாக' என்பதன் எதிர்ச்சொல் என்ன?", "ஓட்டம்", "மெதுவாக", "குதித்து", "பறந்து", answer=1),
        q("'நல்வழி' என்பதன் எதிர்ச்சொல் என்ன?", "பாதை", "துர்வழி", "சாலை", "பயணம்", answer=1),
        q("'இனிப்பு' என்பதன் எதிர்ச்சொல் என்ன?", "கசப்பு", "காரம்", "உப்பு", "புளிப்பு", answer=0),
        q("'அன்பு' என்பதன் எதிர்ச்சொல் என்ன?", "பாசம்", "வெறுப்பு", "சிரிப்பு", "மகிழ்ச்சி", answer=1),
        q("'நீளம்' என்பதன் எதிர்ச்சொல் என்ன?", "உயரம்", "குட்டை", "அகலம்", "பருமனான", answer=1),
        q("'உயரம்' என்பதன் எதிர்ச்சொல் என்ன?", "நீளம்", "குள்ளம்", "அகலம்", "பெரிய", answer=1),
        q("'முன்னே' என்பதன் எதிர்ச்சொல் என்ன?", "மேலே", "பின்னே", "கீழே", "உள்ளே", answer=1),
        q("'நண்பன்' என்பதன் எதிர்ச்சொல் என்ன?", "எதிரி", "உறவினர்", "தம்பி", "அக்கா", answer=0),
        q("'புதிய' என்பதன் எதிர்ச்சொல் என்ன?", "அழகான", "பழைய", "நல்ல", "பெரிய", answer=1),
        q("'திற' என்பதன் எதிர்ச்சொல் என்ன?", "மூடு", "செல்", "வா", "எழு", answer=0)
    ))
)

@Composable
private fun KidsQuizApp(speak: (String)->Unit, sound: (Boolean)->Unit) {
    var screen by remember { mutableStateOf("home") }
    var cat by remember { mutableIntStateOf(0) }
    var question by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var wrongAttempt by remember { mutableStateOf(false) }
    var feedback by remember { mutableStateOf("") }
    var selected by remember { mutableIntStateOf(-1) }

    fun startQuiz(c: Int) { cat=c; question=0; score=0; wrongAttempt=false; selected=-1; feedback=""; screen="quiz"; speak(categories[c].questions[0].q) }
    fun home() { screen="home" }

    Box(Modifier.fillMaxSize().background(Brush.linearGradient(listOf(Color(0xFF70E1F5), Color(0xFFFFD194)))).padding(15.dp), contentAlignment=Alignment.Center) {
        Card(Modifier.fillMaxWidth().widthIn(max=600.dp), shape=RoundedCornerShape(25.dp), colors=CardDefaults.cardColors(Color.White), elevation=CardDefaults.cardElevation(12.dp)) {
            Column(Modifier.padding(22.dp), horizontalAlignment=Alignment.CenterHorizontally) {
                Text("🧠", fontSize=46.sp)
                if (screen == "home") {
                    Text("🌟 குழந்தைகள் அறிவு விளையாட்டு 🌟", fontSize=25.sp, fontWeight=FontWeight.Bold, color=Color(0xFFE91E63), textAlign=TextAlign.Center)
                    Spacer(Modifier.height(10.dp)); Text("விளையாட ஒரு பிரிவைத் தேர்ந்தெடுக்கவும்:", fontSize=16.sp, color=Color.DarkGray)
                    Spacer(Modifier.height(18.dp))
                    Column(verticalArrangement=Arrangement.spacedBy(12.dp)) {
                        categories.forEachIndexed { i, c ->
                            Button(onClick={startQuiz(i)}, modifier=Modifier.fillMaxWidth().height(58.dp), shape=RoundedCornerShape(15.dp), colors=ButtonDefaults.buttonColors(containerColor=listOf(0xFFFF5722,0xFF9C27B0,0xFF2196F3,0xFFFF9800,0xFFE91E63,0xFF009688).let{Color(it[i])})) { Text("${i+1}. ${c.title}", fontSize=17.sp, fontWeight=FontWeight.Bold) }
                        }
                    }
                } else if (screen == "quiz") {
                    val data = categories[cat].questions[question]
                    Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceBetween) { Text(categories[cat].title, fontWeight=FontWeight.Bold); Text("புள்ளிகள்: $score", fontWeight=FontWeight.Bold) }
                    Spacer(Modifier.height(8.dp))
                    IconButton(onClick={speak(data.q)}) { Icon(Icons.Default.VolumeUp, "கேள்வியை கேட்க") }
                    Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)).background(Color(0xFFFFF9C4)).padding(20.dp), contentAlignment=Alignment.Center) { Text(data.q, fontSize=21.sp, fontWeight=FontWeight.Bold, textAlign=TextAlign.Center) }
                    Spacer(Modifier.height(8.dp)); Text(feedback, color=if(feedback.startsWith("✅")) Color(0xFF4CAF50) else Color(0xFFF44336), fontWeight=FontWeight.Bold, minLines=1)
                    Text("கேள்வி: ${question+1} / 20", fontWeight=FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Column(verticalArrangement=Arrangement.spacedBy(10.dp)) {
                        data.options.forEachIndexed { i, opt ->
                            val c = when { selected==i && i==data.answer -> Color(0xFF8BC34A); selected==i && i!=data.answer -> Color(0xFFFF5252); else -> Color(0xFFE0E0E0) }
                            Button(onClick={
                                if (i == data.answer) {
                                    selected = i
                                    feedback = "✅ சரியானது! சபாஷ்!"
                                    sound(true)
                                    speak("சரியானது! சபாஷ்!")
                                    if (!wrongAttempt) score++
                                    question += 1
                                    if (question >= 20) {
                                        screen = "result"
                                    } else {
                                        selected = -1
                                        wrongAttempt = false
                                        feedback = ""
                                        speak(categories[cat].questions[question].q)
                                    }
                                } else {
                                    wrongAttempt = true
                                    selected = i
                                    feedback = "❌ பிழையானது! மீண்டும் முயற்சி செய்!"
                                    sound(false)
                                    speak("பிழையானது! மீண்டும் முயற்சி செய்!")
                                }
                            }, modifier=Modifier.fillMaxWidth().height(55.dp), shape=RoundedCornerShape(12.dp), colors=ButtonDefaults.buttonColors(containerColor=c)) { Text(opt, fontSize=18.sp, fontWeight=FontWeight.Bold, color=if(c==Color(0xFFE0E0E0)) Color.DarkGray else Color.White) }
                        }
                    }
                    Spacer(Modifier.height(12.dp)); OutlinedButton(onClick={home}) { Text("முகப்புக்குச் செல்") }
                } else {
                    Text("🎉 வாழ்த்துகள்! 🎉", fontSize=26.sp, fontWeight=FontWeight.Bold, color=Color(0xFFE91E63))
                    Spacer(Modifier.height(12.dp)); Text("நீங்கள் பெற்ற புள்ளிகள்:", fontSize=20.sp)
                    Text("$score / 20", fontSize=38.sp, fontWeight=FontWeight.Bold, color=Color(0xFF4CAF50))
                    Text(if(score>16) "⭐⭐⭐" else if(score>10) "⭐⭐" else "⭐", fontSize=42.sp)
                    Spacer(Modifier.height(12.dp)); Button(onClick={home}, modifier=Modifier.fillMaxWidth().height(55.dp)) { Text("மீண்டும் விளையாடு 🔄", fontSize=18.sp) }
                    LaunchedEffect(Unit) { speak("வாழ்த்துகள்! விளையாட்டு முடிந்தது!"); sound(true) }
                }
                Spacer(Modifier.height(20.dp)); Text("Created by Mohmmed Ifas", fontSize=13.sp, color=Color.Gray, fontWeight=FontWeight.Bold)
            }
        }
    }
}
