import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.TotalContentApiClient
import io.ktor.client.plugins.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainDisplay()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}

@Composable
fun MainTitle() {
    Text(
        "Title",
        fontSize = 45.sp,
        modifier = androidx.compose.ui.Modifier.fillMaxWidth().padding(30.dp),
        textAlign = TextAlign.Center
    )
}


@Composable
fun MainDisplay() {
    var text by remember { mutableStateOf("Click the download data") }
    var totalContentNumber by remember { mutableStateOf("Loading...") }
    val scope = rememberCoroutineScope()
    Column(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {
        MainTitle()
        Button(
            modifier = androidx.compose.ui.Modifier.size(500.dp).fillMaxWidth().align(Alignment.CenterHorizontally),
            onClick = {

                scope.launch {
                    try {
                        //totalContentNumber = TotalContentApiClient.getAllContent().events.toString()
                        //totalContentNumber = TotalContentApiClient.getDailyContent().messages.get(2).message.CLIENT_IP
                        //writeToExcel(arrayOf(TotalContentApiClient.getDailyContent()))
                        writeToExcel()
                        println("Başarılı!")

                    } catch (e: ClientRequestException) {
                        println("Error Result : ${e.message}")
                    }
                }

                text = totalContentNumber
            }) {
            Text(text, fontSize = 40.sp)
        }
    }
}


private val excelColumns = arrayOf("Timestamp", "UserIP", "UserMac", "DestinationIP")


private suspend fun writeToExcel() {

    GlobalScope.launch {

        val dailyArray = arrayOf(TotalContentApiClient.getDailyContent())
        val excelWorkBook = XSSFWorkbook()
        val createHelper = excelWorkBook.creationHelper

        val sheet = excelWorkBook.createSheet("LOGS")

        val headerFont = excelWorkBook.createFont()
        headerFont.color = IndexedColors.BLUE.getIndex()
        headerFont.bold

        val headerCellStyle = excelWorkBook.createCellStyle()
        headerCellStyle.setFont(headerFont)

        val headerRow = sheet.createRow(0) //initialize 1st row

        //create 1st row
        for (col in excelColumns.indices) {
            val cell = headerRow.createCell(col)
            cell.setCellValue(excelColumns[col])
            cell.cellStyle = headerCellStyle
        }


        //cell style for age
        val ageCellStyle = excelWorkBook.createCellStyle()
        ageCellStyle.dataFormat = createHelper.createDataFormat().getFormat("#")

        var rowIndex = 1
        for (data in dailyArray) {
            (data.messages.forEach {
                val row = sheet.createRow(rowIndex++)
                row.createCell(0).setCellValue(it.message.SYSLOGTIMESTAMP)
                row.createCell(1).setCellValue(it.message.CLIENT_IP)
                row.createCell(2).setCellValue(it.message.CLIENT_MAC)
                row.createCell(3).setCellValue(it.message.DST_IP)

                val dataCell = row.createCell(4)
                dataCell.cellStyle = ageCellStyle
            })
            //
            //row.createCell(2).setCellValue(data.messages[1].message.CLIENT_MAC)
            //row.createCell(3).setCellValue(data.messages[1].message.DST_IP)


        }

        val generatedExcelFile = FileOutputStream("filename.xlsx")
        excelWorkBook.write(generatedExcelFile)
        excelWorkBook.close()
        println("Islem Tamamlandi.")
    }
}



