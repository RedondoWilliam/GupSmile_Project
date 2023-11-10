package gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.sbSnsHomeSn.homeSbSnsEs.homeSnMyReviews

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensayo.example.ui.commonElements.textCommonHomePageString
import gupsmile.com.R
import gupsmile.com.data.MyModuleAuthentication
import gupsmile.com.model.Note
import gupsmile.com.ui.commonElements.ActionsListEmergentMenuItem
import gupsmile.com.ui.commonElements.DialogOneOptionPdCnEs
import gupsmile.com.ui.commonElements.DialogOptionsPersonalized
import gupsmile.com.ui.commonElements.EmergentMenuPd
import gupsmile.com.ui.commonElements.PrincipalIconEmergentMenuItem
import gupsmile.com.ui.mainScreens.homeScreen.homeScreenElements.homeCardContentItem.homeCardContentElements.HomeCardContentItem
import gupsmile.com.ui.mainScreens.messagesScreen.messagesSnEs.DialogWithoutChats
import gupsmile.com.ui.navigationApp.RoutesMainScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MyReviewsSbSnPlCl(
    modifier: Modifier = Modifier
){
    val scope = rememberCoroutineScope()
    var expandedMenuOptionItemReview by rememberSaveable { mutableStateOf(false) }
     var idReviewItem by remember { mutableStateOf("") }

    val context = LocalContext.current
    val fireStore = MyModuleAuthentication.provideFireStoreManagerInstance(context)
    val notes by fireStore.getNotesFlow().collectAsState(emptyList())

    var showDialogAlertAction by remember{ mutableStateOf(false) }
    var showDialogDeleteConfirm by remember{ mutableStateOf(false) }
    var onDeleteNoteConfirmed: () -> Unit = {
        CoroutineScope(Dispatchers.Default).launch {
            fireStore.deleteNote(noteId = idReviewItem)
        }
    }
    var onAddNote:(Note) -> Unit = {
        scope.launch {
            fireStore.addNote(it)
        }
    }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
   Box(
       contentAlignment = Alignment.BottomStart
   ) {
       MyReviewsSbSn(
           textUser = content,
           onTextChange = {content = it},
           onConfirmActionsBottom = {
               val newNote = Note(
                    title = title,
                    content = content
               )
               onAddNote(newNote)
               title = ""
               content = ""

           },
           onDimissBottom = {},
           onDoneClicked = {},
           listReviewsScn = {
               if(!notes.isNullOrEmpty()){
                   notes.forEach{
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
                                   expandedMenuOptionItemReview = !expandedMenuOptionItemReview
                                   idReviewItem = it.id.toString()
                               }
                           )
                   }
               }else{
                   Spacer(modifier = modifier.height(30.dp))
                   DialogWithoutChats(
                       contentTextDialog = R.string.without_reviews_dialog_text
                   )
               }
           },
       )
       AnimatedVisibility(
           visible = expandedMenuOptionItemReview,
           enter = expandVertically(),
           exit = shrinkVertically()
       ) {
           EmergentMenuPd(
               principalIconActions = {
                   PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.hide_icon)
                   PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.no_coments_icon)
                   PrincipalIconEmergentMenuItem(drawableIcon = R.drawable.block_icon)

               },
               actionsListEmergentMenu = {
                   ActionsListEmergentMenuItem(
                       drawableIcon = R.drawable.edit_icon,
                       titileTextAction =R.string.name_list_action_edit_review,
                       actionsItemListBottom = {}
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