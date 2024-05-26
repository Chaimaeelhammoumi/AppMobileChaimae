package com.myapplicationsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivityAffichage extends AppCompatActivity {

    TextView tvAffichage;
    DatabaseHandler db;
    String dataList;
    Button bsingout; // Declare the Sign Out button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_affichage);

        tvAffichage = findViewById(R.id.tvAffichage);
        Button btnDelete = findViewById(R.id.bSupprimer); // Find the delete Button by its id
        Button btnEdit = findViewById(R.id.bModifier); // Find the edit Button by its id
        Button btnAddClient = findViewById(R.id.bAjouter); // Find the add client Button by its id
        bsingout = findViewById(R.id.bsingout); // Find the Sign Out button by its id

        db = new DatabaseHandler(this);

        displayClients();

        // Set onClickListener for the delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllClients(); // Call the deleteAllClients method when the delete button is clicked
            }
        });

        // Set onClickListener for the edit button
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editClient(); // Call the editClient method when the edit button is clicked
            }
        });

        // Set onClickListener for the add client button
        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClient(); // Call the addClient method when the add client button is clicked
            }
        });

        // Set onClickListener for the Sign Out button
        bsingout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(); // Call the signOut method when the Sign Out button is clicked
            }
        });
    }

    // Method to display all clients in the TextView
    private void displayClients() {
        List<Client> clientList = db.getAllClients();

        StringBuilder dataListBuilder = new StringBuilder(); // Initialize StringBuilder

        for (Client client : clientList) {
            dataListBuilder.append(client.toString()); // Append client data
        }

        dataList = dataListBuilder.toString(); // Convert StringBuilder to String

        tvAffichage.setText(dataList);
    }

    // Method to delete all clients from the database
    private void deleteAllClients() {
        List<Client> clientList = db.getAllClients();
        for (Client client : clientList) {
            db.deleteAllClients(client.getId()); // Delete each client one by one
        }
        displayClients(); // Refresh the displayed client list after deletion
    }

    // Method to handle editing client information
    private void editClient() {
        // Start a new activity for the sign-up page
        Intent intent = new Intent(MainActivityAffichage.this, MainActivitySignUp.class);
        startActivity(intent);

        // Finish the current activity
        finish();
    }

    // Method to handle adding a client
    private void addClient() {
        // Start a new activity for the sign-in page
        Intent intent = new Intent(MainActivityAffichage.this, MainActivitySignUp.class);
        startActivity(intent);

        // Finish the current activity
        finish();
    }

    // Method to handle sign out
    private void signOut() {
        // Navigate back to the main activity or any desired destination
        Intent intent = new Intent(MainActivityAffichage.this, MainActivity.class);
        startActivity(intent);
           // Finish the current activity
        finish();
    }
}
