package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.grid.LazyGridItemScopeImpl.animateItemPlacement
//import androidx.compose.foundation.lazy.grid.LazyGridItemScopeImpl.animateItemPlacement
//import androidx.compose.foundation.lazy.grid.LazyGridItemScopeImpl.animateItemPlacement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.draw.EmptyBuildDrawCacheParams.density
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
//import gupsmile.com.data.temporalConfig.StateAddNewNote
import gupsmile.com.data.temporalConfig.StateChangesOnListReviews
import gupsmile.com.data.temporalConfig.ViewModelGetReviews
import gupsmile.com.model.Note
import gupsmile.com.ui.commonElements.ActionsListEmergentMenuItem
//import gupsmile.com.ui.commonElements.BarProgressAnimation
import gupsmile.com.ui.commonElements.DialogOneOptionPdCnEs
import gupsmile.com.ui.commonElements.DialogOptionsPersonalized
import gupsmile.com.ui.commonElements.EmergentMenuPd
import gupsmile.com.ui.commonElements.PrincipalIconEmergentMenuItem
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements.HomeCardContentItem
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.subscreensPanelControl.subscreensManagerState.ViewModelHorizontalPagerPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.stream.IntStream
import kotlin.random.Random



/**Panel de control para la pantalla de lista de Gups
 * */
@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyReviewsSbSnPlCl(
    modifier: Modifier = Modifier,
    viewModelGetReviews: ViewModelGetReviews?,
    navController: NavHostController
){

    val viewModelHorizontalPager: ViewModelHorizontalPagerPage = viewModel()
    val horizontalPagerUiState = viewModelHorizontalPager.uiState.collectAsState().value

    var heigtSecundaryBox by remember{ mutableStateOf(250.dp) }

    val pointerAction: () -> Unit = {
        viewModelHorizontalPager.updateStateImage(false)
        viewModelHorizontalPager.updateStateNewGup(false)
        viewModelHorizontalPager.updateStateUpdateGup(false)
    }

    BackHandler {
       pointerAction()
        if(horizontalPagerUiState.stateNewGup){
            viewModelHorizontalPager.updateStateNewGup(false)
        }
        if(horizontalPagerUiState.pauseGlobalUi){
            viewModelHorizontalPager.updatePauseGlobalStateUi(false)
        }
        if(horizontalPagerUiState.stateUpdateGup){
            viewModelHorizontalPager.updateStateUpdateGup(false)
        }
    }

    val listState = rememberLazyListState()
    val viewModelGetReviewsUiState = viewModelGetReviews!!.uiState.collectAsState().value
    val notesView = viewModelGetReviewsUiState.stableNotes


    var idReviewItem by remember { mutableStateOf("") }

    var showDialogAlertAction by remember{ mutableStateOf(false) }
    var showDialogDeleteConfirm by remember{ mutableStateOf(false) }

    var onDeleteNoteConfirmed: () -> Unit = {
        viewModelGetReviews.updateStateChangesOnListReviews(newValue = StateChangesOnListReviews.NEWCHANGES)
        CoroutineScope(Dispatchers.Default).launch {
            viewModelGetReviews.deleteNote(idReviewItem)
        }
    }

    var currentGup by remember{ mutableStateOf(Note()) }
    var contentGup by remember{ mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }

    /**  estado de animación para item de Lazycolumn.*/
    var visibleGup by remember{ mutableStateOf(true) }


   Box(
       contentAlignment = Alignment.BottomEnd,
       modifier = modifier
           .fillMaxSize(1f)
   ) {
       MyReviewsSbSn(
           listReviewsScn = {
               if(viewModelGetReviewsUiState.stableNotes.isNotEmpty()){
                   LazyColumn(state = listState){
                       item {
                           AddNewGupAccessScn(
                               bottomActions = {
                                   viewModelHorizontalPager.updateStateNewGup(true)
                                   viewModelHorizontalPager.updatePauseGlobalStateUi(true)
                               }
                           )
                       }
                       items(
                           items = viewModelGetReviewsUiState.stableNotes,
                           key = {
                               it.id!!
                           },
                           contentType = {it.content}
                       ){
                           Row(
                               modifier = modifier
                                   .animateItemPlacement(
                                       tween(durationMillis = 500)
                                   )
                           ) {
                               HomeCardContentItem(
                                   nameUser = "Lucia Hernández",
                                   timePost = "hace 30 minutos" ,
                                   reactionsLikes = "1.2k",
                                   reactionsComments = "145",
                                   addContentMedia = {},
                                   descriptionPost = {
                                       textCommonHomePageString(
                                           stringResTextEntry = "${it.content}" ,
                                           maxLinesResParameter = 100 ,
                                           lineHeightParameter = 14.sp ,
                                           fontSizeStyleParameter = 14.sp,
                                           fontFamilyStyleParameter = FontFamily(Font(R.font.raleway_light)),
                                           colorStyleParameter = MaterialTheme.colorScheme.onBackground
                                       )
                                   },
                                   actionsMenuBottom = {
                                       if (heigtSecundaryBox < 250.dp) {
                                           heigtSecundaryBox = 250.dp
                                       }
                                       idReviewItem = it.id.toString()
                                       contentGup = it.content
                                       viewModelHorizontalPager.updateStateImage(true)
                                       currentGup = it
                                   },
                                   notificationCharge = {
                                       if (it.backendState){
                                           Icon(
                                               painter = painterResource(id = R.drawable.charge_error_icon),
                                               contentDescription = "Search Icon",
                                               tint = MaterialTheme.colorScheme.primary,
                                               modifier = Modifier
                                                   .size(25.dp)
                                           )
                                       }
                                   }
                               )
                           }

                       }

                   }
               }else{
                   LazyColumn(state = listState){
                       item {
                           AddNewGupAccessScn(
                               bottomActions = {
                                   viewModelHorizontalPager.updateStateNewGup(true)
                                   viewModelHorizontalPager.updatePauseGlobalStateUi(true)
                               }
                           )
                       }
                   }
               }

           },
       )
       if(
           horizontalPagerUiState.stateImage || horizontalPagerUiState.stateNewGup ||
           horizontalPagerUiState.stateUpdateGup
           ){
           Box(
               modifier = modifier
                   .fillMaxWidth()
                   .fillMaxSize(1f)
                   .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                   .pointerInput(Unit) {
                       detectTapGestures(
                           onTap = {
                               pointerAction()
                           }
                       )
                   }

           )
       }
       AnimatedVisibility(
           visible = horizontalPagerUiState.stateImage,
           enter = slideInVertically(
               initialOffsetY =  {it*2},
               animationSpec = tween(durationMillis = 300, delayMillis = 0)
           ),
           exit = slideOutVertically(
               targetOffsetY = {it*2},
               animationSpec = tween(durationMillis = 300, delayMillis =0,)
           )
       ) {
           ScrollableOptionsDownMenu(
               defaultSize = 250F,
               controlHeightState = {
                   viewModelHorizontalPager.updateStateImage(false)
                                    },
               contentComposableMenu = {
                   EmergentMenuPd(
                       principalIconActions = {
                           PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.hide_icon)
                           Spacer(modifier = Modifier.width(28.dp))
                           PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.no_coments_icon)
                           Spacer(modifier = Modifier.width(28.dp))
                           PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.block_icon)

                       },
                       actionsListEmergentMenu = {
                           ActionsListEmergentMenuItem(
                               drawableIcon = R.drawable.edit_icon,
                               titileTextAction =R.string.name_list_action_edit_review,
                               actionsItemListBottom = {
                                   viewModelHorizontalPager.updateStateUpdateGup(true)
                                   viewModelHorizontalPager.updateStateImage(false)
                               }
                           )
                           ActionsListEmergentMenuItem(
                               drawableIcon = R.drawable.delete_icon ,
                               titileTextAction = R.string.name_list_action_delete_review,
                               actionsItemListBottom = {showDialogAlertAction = !showDialogAlertAction},
                               idReview = idReviewItem
                           )
                       },
                       modifier = Modifier

                   )
               }
           )
       }

       AnimatedVisibility(
           visible = horizontalPagerUiState.stateNewGup || horizontalPagerUiState.stateUpdateGup,
           enter = slideInVertically(
               initialOffsetY =  {it*2},
               animationSpec = tween(durationMillis = 300, delayMillis = 0)
           ),
           exit = slideOutVertically(
               targetOffsetY = {it*2},
               animationSpec = tween(durationMillis = 300, delayMillis =0,)
           )
       ) {
           ScrollableOptionsDownMenu(
               defaultSize = 800F,
               controlHeightState = {
                   viewModelHorizontalPager.updateStateNewGup(false)
                   viewModelHorizontalPager.updatePauseGlobalStateUi(false)
                   viewModelHorizontalPager.updateStateUpdateGup(false)
                                    },
               contentComposableMenu = {
                  if(horizontalPagerUiState.stateNewGup){
                      AddNewGupSn(
                          contentHistoryText = content,
                          contentHistoryOnTextChange = {content = it} ,
                          doneBottomAction = {
                              scope.launch {
                                  viewModelHorizontalPager.updateStateNewGup(false)
                                  viewModelHorizontalPager.updatePauseGlobalStateUi(false)
                                  val newNote = Note(
                                      content = content,
                                      orderList = currentGup.orderList,
                                      secondId = generateRandomTenDigitsNumber()
                                  )
                                  val addGup = async {
                                      viewModelGetReviews.addNote(note = newNote)
                                  }
                                  addGup.join()
                                  viewModelGetReviews.updateTemporalGup(newNote)
                                  content = ""
                                  viewModelGetReviews.updateStateChangesOnListReviews(newValue = StateChangesOnListReviews.NEWCHANGES)
                              }
                          },
                          arrowBackTopBottom = {
                              viewModelHorizontalPager.updateStateNewGup(false)
                              viewModelHorizontalPager.updatePauseGlobalStateUi(false)
                          },
                          openTopMenuOptions = {expanded = !expanded},
                          titleActionGup = R.string.title_sn_addnewgup,
                          titleDoneActionGup = R.string.title_bottom_post_history
                      )
                  }
                  if(horizontalPagerUiState.stateUpdateGup){
                      AddNewGupSn(
                          contentHistoryText = contentGup,
                          contentHistoryOnTextChange = {contentGup = it} ,
                          doneBottomAction = {
                              viewModelHorizontalPager.updateStateImage(false)
                              viewModelHorizontalPager.updateStateUpdateGup(false)
                              viewModelHorizontalPager.updateStateNewGup(false)
                              viewModelHorizontalPager.updatePauseGlobalStateUi(false)
                              scope.launch {
                                  val newNote = Note(
                                      content = contentGup,
                                      dateNote = currentGup.dateNote,
                                      id = currentGup.id,
                                      orderList = currentGup.orderList,
                                      secondId = currentGup.secondId

                                  )
                                  viewModelGetReviews.updateNote(note = newNote, noteId =idReviewItem)
                                  contentGup = ""
                                  viewModelGetReviews.updateStateChangesOnListReviews(newValue = StateChangesOnListReviews.NEWCHANGES)
                              }

                          },
                          arrowBackTopBottom = {
                              viewModelHorizontalPager.updateStateNewGup(false)
                              viewModelHorizontalPager.updatePauseGlobalStateUi(false)
                          },
                          openTopMenuOptions = {expanded = !expanded},
                          titleActionGup = R.string.title_sn_update_gup,
                          titleDoneActionGup = R.string.title_bottom_post_history
                      )
                  }
               }
           )
       }


       if(showDialogAlertAction){
           DialogOptionsPersonalized(
               onDismiss = { showDialogAlertAction = false },
               onConfirmActions = {
                   onDeleteNoteConfirmed()
                   showDialogAlertAction = false
                   showDialogDeleteConfirm = true
               },
               textCancelBottom = R.string.text_action_bottom_cancel,
               textConfirmBottom = R.string.text_action_bottom_aceptar,
               textInfoDialogMenu = R.string.text_content_dialog_delete_review,
               iconDialogMenu = R.drawable.warning_icon
           )
       }
       if(showDialogDeleteConfirm){
           DialogOneOptionPdCnEs(
               onDismiss = { /*TODO*/ },
               onConfirmActions = {
                   showDialogDeleteConfirm = false
               },
               textConfirmBottom = R.string.title_bottom_dialog_confirm_delete_contact,
               textInfoDialogMenu = R.string.title_dialog_confirm_delete_review,
               iconDialogMenu = R.drawable.succes_create_account_icon
           )
       }
   }
}


@Composable
fun ScrollableOptionsDownMenu(
    modifier: Modifier = Modifier,
    defaultSize: Float,
    contentComposableMenu: @Composable () -> Unit,
    controlHeightState: () -> Unit
){
    var heigtSecundaryBox by remember{ mutableStateOf(defaultSize.toInt().dp) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 25.dp,
                    topEnd = 25.dp
                )
            )
            .background(MaterialTheme.colorScheme.background)
            .height(heigtSecundaryBox)
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    if (heigtSecundaryBox - delta.dp <= defaultSize.toInt().dp) {
                        heigtSecundaryBox -= delta.dp
                    } else {
                        heigtSecundaryBox = defaultSize.toInt().dp
                    }
                    delta
                },
                onDragStarted = {},
                onDragStopped = {
                    while (heigtSecundaryBox > defaultSize.toInt().dp / 2 && heigtSecundaryBox < defaultSize.toInt().dp) {
                        heigtSecundaryBox += (defaultSize * 0.024).toInt().dp
                        delay(5)
                    }
                    while (heigtSecundaryBox > 10.dp && heigtSecundaryBox <= defaultSize.toInt().dp / 2) {
                        heigtSecundaryBox -= (defaultSize * 0.024).toInt().dp
                        delay(5)
                    }
                    if (heigtSecundaryBox <= 10.dp) {
                        controlHeightState()
                    }
                }
            )
    ){
        contentComposableMenu()
    }
}


/**
 * Establece un número de 20 dígitos que va a funcionar como id sucundaria para cada Gup generado por
 * el usuario*/
fun generateRandomTenDigitsNumber(): String {
    val random = Random(System.currentTimeMillis())
    var stringNumber = ""
    while (stringNumber.length <= 19){
        if(stringNumber != ""){
            stringNumber += random.nextInt(9).toString()
        }else{
            stringNumber =  random.nextInt(9).toString()
        }
    }
    stringNumber.toCharArray().shuffle()
    stringNumber
    return stringNumber
}

fun main(){
    println(generateRandomTenDigitsNumber() + "este es el resultado")


}