package tw.edu.pu.csim.qinghong179-spec.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {

    // 使用 Box 來疊加不同的 Composable 元件
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 設定背景色
    ) {
        // --- 1. 顯示分數和姓名 (疊在最上層) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter) // 靠上方中央對齊
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 顯示分數
            Text(
                text = "分數: ${gameViewModel.score}",
                fontSize = 32.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            // 【新增】顯示您的姓名
            Text(
                text = "姓名: 洪詩晴",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            // 顯示訊息
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // --- 2. 遊戲畫布 (顯示圓形) ---
        Canvas(modifier = Modifier.fillMaxSize()) {
            val circlePosition = Offset(gameViewModel.circleX, gameViewModel.circleY)
            val circleRadius = 50f // 假設半徑為 50 像素

            // 繪製紅色的圓
            drawCircle(
                color = Color.Red,
                radius = circleRadius,
                center = circlePosition
            )
        }
    }
}
