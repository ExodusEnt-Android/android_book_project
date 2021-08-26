package org.techtown.bookprojectjungsang.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.techtown.bookprojectjungsang.R
import org.techtown.bookprojectjungsang.compose.ui.theme.BookProjectJungSangTheme
import org.techtown.bookprojectjungsang.model.Note
import org.techtown.bookprojectjungsang.viewmodel.NotesViewModel

class NotesFragment : Fragment() {

    private val notesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column {
                    AddNote(title = "Add Note"){
                        notesViewModel.addNote(it)
                    }

                    ShowNotes(notesViewModel.notes) {
                        notesViewModel.remoteNote(it)
                    }
                }
            }
        }
    }

}

@Composable
fun AddNote(title: String, onNoteAdded: (String) -> Unit) {
    Row {
       val text = remember {
           mutableStateOf(TextFieldValue(""))
       }

        TextField(value = text.value,
            onValueChange = { text.value = it },
            label = { Text(title) },
            modifier = Modifier
                .weight(1f, true)
                .padding(16.dp, 16.dp, 8.dp, 16.dp))

        Button(onClick = {
            val newNote = text.value.text
            if (newNote.isNotBlank()) {
                onNoteAdded(newNote)
                text.value = TextFieldValue("")
            }
        },
            modifier = Modifier
                .padding(8.dp, 16.dp, 16.dp, 16.dp)
        ) {
            Icon(imageVector =  Icons.Filled.Add, contentDescription = null,modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun ShowNotes(items: List<Note>, onNoteAdded: (Note) -> Unit){
    LazyColumn{


      items(items){ model ->
          Row {
              Text(
                  text = model.content,
                  modifier = Modifier
                      .padding(16.dp, 4.dp, 4.dp, 4.dp)
                      .weight(1f, true)
                      .wrapContentWidth(Alignment.End)
              )

              TextButton(
                  onClick = { onNoteAdded(model) },
                  contentPadding = PaddingValues(horizontal = 16.dp,vertical = 8.dp),
                  modifier = Modifier
                      .padding(4.dp, 4.dp, 16.dp, 4.dp)
                      .wrapContentHeight(Alignment.CenterVertically),
                  ) {
                  Icon(imageVector =  Icons.Filled.Delete, contentDescription = null,modifier = Modifier.size(24.dp))
              }
          }

      }
    }
}