import org.apache.tools.ant.filters.*
import org.gradle.api.artifacts.dsl.*
import org.gradle.api.file.*
import org.gradle.api.internal.*
import org.gradle.internal.os.OperatingSystem.*
import org.gradle.kotlin.dsl.*
import java.text.*
import java.util.*

inline val timestamp get() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(Date())

inline val AbstractTask.neverUpToDate get() = outputs.upToDateWhen { false }

fun ContentFilterable.replaceTokens(properties: Map<String, Any>) = filter<ReplaceTokens>("tokens" to properties)
fun ContentFilterable.replaceTokens(vararg properties: Pair<String, Any>) = replaceTokens(mapOf(*properties))
