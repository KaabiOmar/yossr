package tn.esprit.tourbuddy.ui.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.IOException;

import tn.esprit.tourbuddy.R;

public class NewPublicationFragment extends Fragment {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Button btnSubmitPublication;
    private Button btnUploadImage;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_publication, container, false);

        editTextTitle = view.findViewById(R.id.edit_text_title);
        editTextContent = view.findViewById(R.id.edit_text_content);
        btnSubmitPublication = view.findViewById(R.id.btn_add_publication);
        btnUploadImage = view.findViewById(R.id.btn_upload_image);

        btnSubmitPublication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();

                // Vérifier si le titre et le contenu ne sont pas vides avant de continuer
                if (!title.isEmpty() && !content.isEmpty()) {
                    // Enregistrer dans la base de données ou effectuer l'action souhaitée
                    // ...

                    // Retourner en arrière
                    getParentFragmentManager().popBackStack();
                } else {
                    Toast.makeText(requireContext(), "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        return view;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                // Vous pouvez également utiliser Picasso ou une autre bibliothèque pour afficher l'image
                // Dans cet exemple, nous utilisons directement l'URI pour l'afficher dans un ImageView
                // ImageView imageView = findViewById(R.id.image_view);
                // imageView.setImageURI(imageUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
