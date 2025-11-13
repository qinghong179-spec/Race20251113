package tw.edu.pu.csim.qinghong179-spec.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(false)

    var circleX by mutableStateOf(0f)
    var circleY by mutableStateOf(0f)

    // 【新增】儲存分數的變數
    var score by mutableStateOf(0)
        private set

    // 設定螢幕寬度與高度
    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
        // 首次設定大小時，啟動遊戲（如果尚未啟動）
        if (!gameRunning) {
            gameRunning = true
            StartGame()
        }
    }

    fun StartGame() {
        //回到初使位置
        circleX = 100f
        circleY = screenHeightPx - 100f

        viewModelScope.launch {
            while (gameRunning) { // 每0.1秒循環
                delay(100)
                circleX += 10

                // 【修改】判斷圓形是否碰到右邊邊界
                // 假設圓形半徑為 50f (100f / 2)
                val circleRadius = 50f
                if (circleX >= screenWidthPx - circleRadius) {
                    // 【新增】分數+1
                    score++
                    // 重設圓形位置到左邊
                    circleX = circleRadius
                }
            }
        }
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }
}