package tw.edu.pu.csim.qinghong179-spec.race2025

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize // 確保這個引用還在 (雖然GameScreen裡也會用到)
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.window.layout.WindowMetricsCalculator
import tw.edu.pu.csim.qinghong179-spec.race2025.ui.theme.RaceTheme

// 確保有引入 GameScreen
import tw.edu.pu.csim.qinghong179-spec.race2025.GameScreen // 如果放在同一個 package 內則可省略

class MainActivity : ComponentActivity() {

    // 取得 GameViewModel 實例
    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //強迫橫式螢幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        // 隱藏狀態列：獲取 WindowInsetsController，再隱藏statusBars
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())

        //隱藏下方巡覽列
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

        // 確保內容延伸到至邊緣
        WindowCompat.setDecorFitsSystemWindows(
            window, false)

        // 步驟 1: 獲取 WindowMetricsCalculator 實例
        val windowMetricsCalculator =
            WindowMetricsCalculator.getOrCreate()

        // 步驟 2: 計算當前視窗的 WindowMetrics
        val currentWindowMetrics=
            windowMetricsCalculator.computeCurrentWindowMetrics(this)

        // 步驟 3: 從 bounds 獲取像素尺寸
        val bounds = currentWindowMetrics.bounds

        val screenWidthPx = bounds.width().toFloat()
        val screenHeightPx = bounds.height().toFloat()

        // 將尺寸設定給 ViewModel
        gameViewModel.SetGameSize(screenWidthPx , screenHeightPx)

        setContent {
            RaceTheme {
                // 呼叫 GameScreen
                GameScreen(message="橫式螢幕，隱藏狀態列.", gameViewModel = gameViewModel)
            }
        }
    }
}