import androidx.compose.ui.window.CanvasBasedWindow
import br.com.soft.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() = onWasmReady {
    CanvasBasedWindow(canvasElementId = "appTarget") {
        App()
    }
}