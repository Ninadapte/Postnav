package com.project.postnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.postnav.HomeActivity;
import com.project.postnav.MainActivity;
import com.project.postnav.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
/*
* , "Jammu and Kashmir6",
                "Jharkhand7", "West Bengal8", "Karnataka9", "Kerala10"," Madhya Pradesh11"," Maharashtra12", "Manipur13", "Meghalaya14",
                "Mizoram15", "Nagaland16", "Orissa17"," Punjab18", "Rajasthan19"," Sikkim20"," Tamil Nadu21"," Tripura22"," Uttaranchal23"," Uttar Pradesh24",
                "Haryana25", "Himachal Pradesh26",  "Chhattisgarh27"*/
public class AuthenticationActivity extends AppCompatActivity {

    private DatabaseReference FireUser = FirebaseDatabase.getInstance().getReference();
    public static String State;
    public static String District;

    static String email_address;
    static String Name;
    String[] items2;
    ArrayAdapter<String> adapter2;

   FirebaseUser muser;
    FirebaseAuth mauth;
    FirebaseDatabase mdata;
    static SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        sharedpreferences = this.getSharedPreferences("SharedPref",Context.MODE_PRIVATE);
        final Spinner dropdown2 = findViewById(R.id.spinner2);
        final Spinner dropdown3 = findViewById(R.id.spinner3);
        dropdown3.setVerticalScrollBarEnabled(true);
        dropdown2.setVerticalScrollBarEnabled(true);
        final DatabaseReference ref = MainActivity.y;
        final Spinner dropdown = findViewById(R.id.spinner1);
        dropdown.setVerticalScrollBarEnabled(true);
        String [] items3 = new String[]{"Employee","Administrator"};
        String[] items = new String[]{"Andhra Pradesh", "Assam", "Arunachal Pradesh", "Bihar", "Goa", "Gujarat","Jammu and Kashmir",
                "Jharkhand", "West Bengal", "Karnataka", "Kerala"," Madhya Pradesh"," Maharashtra", "Manipur", "Meghalaya",
                "Mizoram", "Nagaland", "Orissa"," Punjab", "Rajasthan"," Sikkim"," Tamil Nadu"," Tripura"," Uttaranchal"," Uttar Pradesh",
                "Haryana", "Himachal Pradesh",  "Chhattisgarh27"};
        mauth = FirebaseAuth.getInstance();
        muser= mauth.getCurrentUser();
       mdata= FireUser.getDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items3);
         items2= new String[]{"SELECT STATE FIRST"};
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);
        dropdown3.setAdapter(adapter3);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0)
                {
                    items2 = new String[]{"East Godavari", "West Godavari", "Krishna", "Guntur", " Prakasam", "Sri Potti Sri Ramulu Nellore", "Srikakulam", "Vizianagaram", "Visakhapatnam"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);

                }else if(position == 1)
                {
                     items2 = new String[]{"Tinskia" ," Dibrugarh ", "Dhemaji"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);

                }
                else if(position == 2)
                {
                     items2 = new String[]{"Changlang","Dibang Valley","East Kameng","East Siang"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);

                }
                else if(position == 3)
                {
                     items2 = new String[]{"Patna","Nalanda","Bhojpur","Rohitas","Kaimur"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);

                }
                else if(position == 4)
                {
                     items2 = new String[]{"North Goa ","South Goa"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);

                }
                else if(position == 5)
                {
                    items2 = new String[]{"Ahmedabad","Amreli","Anand","Aravalli","Banaskantha ","(Palanpur)Bharuch" ,"Bhavnagar","Botad"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 6)
                {
                    items2 = new String[]{"Srinagar"," Budgam"," Pulwama", "Anantnag", "Kupwara", "Baramulla", "Leh" ,"Kargil", "Jammu", "Kathua", "Poonch", "Rajouri", "Udhampur" ,"Doda"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 7)
                {
                    items2 = new String[]{"Bokaro", "Chatra", "Deoghar", "Dhanbad", "Dumka", "East Singhbhum", "Garhwa", "Giridih", "Godda", "Gumla", "Hazaribag ","Jamtara", "Khunti", "Koderma", "Latehar", " Lohardaga", "Pakur", "Palamu", "Ramgarh", "Ranchi", "Sahibganj", "Seraikela-Kharsawan", "Simdega", "West Singhbhum"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 8)
                {
                    items2 = new String[]{"Alipurduar", "Bankura", "Birbhum", "Cooch Behar", "Dakshin Dinajpur (South Dinajpur)", "Darjeeling", "Hooghly", "Howrah"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 9)
                {
                    items2 = new String[]{"Bagalkot", "Ballari (Bellary)", "Belagavi (Belgaum)", "Bengaluru (Bangalore) Rural", "Bengaluru (Bangalore) Urban", "Bidar", "Chamarajanagar", "Chikballapur", "Chikkamagaluru (Chikmagalur)", " Chitradurga", "Dakshina Kannada", "Davangere", "Dharwad", "Gadag", "Hassan", "Haveri", "Kalaburagi (Gulbarga)", "Kodagu", "Kolar", "Koppal", "Mandya", "Mysuru (Mysore)", "Raichur", "Ramanagara", "Shivamogga (Shimoga)", "Tumakuru (Tumkur)", "Udupi", "Uttara Kannada (Karwar)", "Vijayapura (Bijapur)", "Yadgir"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 10)
                {
                    items2 = new String[]{"Thiruvananthapuram", "Kollam", "Alappuzha","Pathanamthitta", "Kottayam", "Idukki", "Ernakulam", "Thrissur", "Palakkad", "Malappuram", "Kozhikode", "Wayanadu", "Kannur and Kasaragod"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 11)
                {
                    items2 = new String[]{"Agar Malwa", "Alirajpur", " Anuppur", "Ashoknagar", "Balaghat", "Barwani", "Betul", "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh", "Datia", " Dewas", "Dhar", "Dindori", "Guna", "Gwalior", "Harda", "Hoshangabad", "Indore", "Jabalpur", "Jhabua", "Katni", "Khandwa", "Khargone", " Mandla", "Mandsaur", " Morena", " Narsinghpur", " Neemuch", "Panna", "Raisen", "Rajgarh", " Ratlam", " Rewa", " Sagar", " Satna", "Sehore", "Seoni", "Shahdol", "Shajapur", "Sheopur", "Shivpuri", "Sidhi", "Singrauli", "Tikamgarh", "Ujjain", "Umaria", "Vidisha"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 12)
                {
                    items2 = new String[]{"Ahmednagar", "Akola", "Amravati","Aurangabad", "Beed", "Bhandara", "Buldhana", "Chandrapur", "Dhule", "Gadchiroli", "Gondia", "Hingoli", "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai City", "Mumbai Suburban", "Nagpur", "Nanded", "Nandurbar", "Nashik", "Osmanabad", "Palghar", "Parbhani", "Pune", "Raigad", "Ratnagiri", " Sangli", "Satara", "Sindhudurg", " Solapur", "Thane", "Wardha", "Washim", "Yavatmal"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 13)
                {
                    items2 = new String[]{"Bishnupur", "Chandel", "Churachandpur", "Imphal East", "Imphal West"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 14)
                {
                    items2 = new String[]{"East Garo Hills", "East Jaintia Hills", "East Khasi Hills", "North Garo Hills", "Ri Bhoi", "South Garo Hills", "South West Garo Hills", "South West Khasi Hills", "West Garo Hills", "West Jaintia Hills", " West Khasi Hills"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 15)
                {
                    items2 = new String[]{"Aizawl", "Champhai", " Kolasib", "Lawngtlai", "Lunglei", "Mamit", "Saiha", "Serchhip"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 16)
                {
                    items2 = new String[]{"Dimapur", "Kiphire", "Kohima", "Longleng", "Mokokchung", "Mon", "Peren", "Phek", "Tuensang", "Wokha", "Zunheboto"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 17)
                {
                    items2 = new String[]{"Angul", "Balangir", "Balasore", "Bargarh", "Bhadrak", "Boudh", "Cuttack", "Deogarh", "Dhenkanal", "Gajapati", "Ganjam", "Jagatsinghapur", "Jajpur", "Jharsuguda", "Kalahandi", "Kandhamal", "Kendrapara", "Kendujhar (Keonjhar)", "Khordha", "Malkangiri", "Mayurbhanj", "Nabarangpur", "Nayagarh", "Nuapada", "Puri", "Rayagada", "Sambalpur", "Sonepur", "Sundargarh"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 18)
                {
                    items2 = new String[]{ "Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib", "Fazilka", "Ferozepur", "Gurdaspur", "Hoshiarpur", "Kapurthala", "Ludhiana", "Mansa", "Moga", "Muktsar", "Nawanshahr (Shahid Bhagat Singh Nagar)", "Pathankot", " Patiala", "Rupnagar", "Sahibzada Ajit Singh Nagar (Mohali)", "Sangrur", "Tarn Taran"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 19)
                {
                    items2 = new String[]{"Ajmer",
                            "Alwar", "Banswara", "Baran", "Barmer", "Bharatpur", "Bhilwara", "Bikaner", "Bundi", "Chittorgarh", "Churu", "Dausa", "Dholpur", "Dungarpur", "Hanumangarh", "Jaipur", "Jaisalmer", "Jalore", "Jhalawar", "Jhunjhunu", "Jodhpur", "Karauli", "Kota", "Nagaur", "Pali", "Pratapgarh", "Rajsamand", "Sawai Madhopur", "Sikar", "Sirohi", "Sri Ganganagar", "Tonk", "Udaipur",
                    };
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 20)
                {
                    items2 = new String[]{"East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 21)
                {
                    items2 = new String[]{"Ariyalur", "Chengalpattu", "Chennai", "Coimbatore", "Cuddalore", "Dharmapuri", "Dindigul", "Erode", "Kallakurichi", "Kanchipuram", "Kanyakumari", "Karur", "Krishnagiri", "Madurai", "Nagapattinam", "Namakkal", "Nilgiris", "Perambalur", "Pudukkottai", "Ramanathapuram", "Ranipet", "Salem", "Sivaganga", "Tenkasi", "Thanjavur", "Theni", "Thoothukudi (Tuticorin)", "Tiruchirappalli", "Tirunelveli", "Tirupathur", "Tiruppur", "Tiruvannamalai", "Tiruvarur", "Vellore", "Viluppuram", "Virudhunagar"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 22)
                {
                    items2 = new String[]{"Dhalai", "Gomati", "Khowai", "North Tripura", "Sepahijala", "South Tripura", "Unakoti", "West Tripura"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 23)
                {
                    items2 = new String[]{"Almora", "Bageshwar", "Chamoli", "Champawat", "Dehradun", "Haridwar", "Nainital", "Pauri Garhwal", "Pithoragarh", "Rudraprayag", "Tehri Garhwal", "Udham Singh Nagar", "Uttarkashi"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 24)
                {
                    items2 = new String[]{"Agra", "Aligarh", "Allahabad", "Ambedkar Nagar", "Amethi (Chatrapati Sahuji Mahraj Nagar)", "Amroha (J.P. Nagar)", "Auraiya", "Azamgarh", "Baghpat", "Bahraich", "Balli", "Balrampur", "Banda", "Barabanki", "Bareilly", "Basti", "Bhadohi", "Bijnor", "Budaun", "Bulandshahr", "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Faizabad", "Farrukhabad", "Fatehpur", "Firozabad", "Gautam Buddha Nagar", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur", "Hamirpur", "Hapur (Panchsheel Nagar)", "Hardoi", "Hathras", "Jalaun", "Jaunpur", "Jhansi", "Kannauj", "Kanpur Dehat", "Kanpur Nagar", "Kanshiram Nagar (Kasganj)", "Kaushambi", "Kushinagar (Padrauna)", "Lakhimpur - Kheri", "Lalitpur", "Lucknow", "Maharajganj", "Mahoba", "Mainpuri", "Mathura", "Mau", "Meerut", "Mirzapur", "Moradabad", "Muzaffarnagar", "Pilibhit", "Pratapgarh", "RaeBareli", "Rampur", "Saharanpur", "Sambhal (Bhim Nagar)", "Sant Kabir Nagar", "Shahjahanpur", "Shamali (Prabuddh Nagar)", "Shravasti", "Siddharth Nagar", "Sitapur", "Sonbhadra", "Sultanpur", "Unnao", "Varanasi"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 25)
                {
                    items2 = new String[]{"Ambala",
                           "Bhiwani", "Charkhi Dadri", "Faridabad", "Fatehabad", "Gurugram (Gurgaon)", "Hisar", "Jhajjar", "Jind", "Kaithal", "Karnal", "Kurukshetra", "Mahendragarh", "Nuh", "Palwal", "Panchkula", "Panipat", "Rewari", "Rohtak", "Sirsa", "Sonipat", "Yamunanagar"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 26)
                {
                    items2 = new String[]{"Bilaspur", "Chamba", "Hamirpur", "Kangra", "Kinnaur", "Kullu", "Lahaul & Spiti", "Mandi", "Shimla", "Sirmaur (Sirmour)", "Solan", "Una"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }
                else if(position == 27)
                {
                    items2 = new String[]{"Balod", "Baloda Bazar", "Balrampur", "Bastar", "Bemetara", "Bijapur", "Bilaspur", "Dantewada (South Bastar)", "Dhamtari", "Durg", "Gariyaband", "Janjgir-Champa", "Jashpur", "Kabirdham (Kawardha)", "Kanker (North Bastar)", "Kondagaon", "Korba", "Korea (Koriya)", "Mahasamund", "Mungeli", "Narayanpur", "Raigarh", "Raipur", "Rajnandgaon", "Sukma", "Surajpur", "Surguja"};
                    adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown2.setAdapter(adapter2);


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dropdown.setAdapter(adapter);



        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //  DatabaseReference childref = mdata.getReference().child("Employees").child(dropdown.getSelectedItem().toString()).child(dropdown2.getSelectedItem().toString()).child(MainActivity.Email2);
               DatabaseReference childref = mdata.getReference().child("Employees").child(MainActivity.Email2);
               childref.child("State").setValue(dropdown.getSelectedItem().toString());
               childref.child("District").setValue(dropdown2.getSelectedItem().toString());
                childref.child("Name").setValue(muser.getDisplayName());
                childref.child("Status").setValue(dropdown3.getSelectedItem().toString());
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Status" ,dropdown3.getSelectedItem().toString() );
                editor.putString("State" ,dropdown.getSelectedItem().toString() );
                editor.putString("District" ,dropdown2.getSelectedItem().toString() );
                editor.commit();
                if(dropdown3.getSelectedItem().toString().equals("Employee"))
                {
                    MainActivity.admin_status = 0;
                }
                else
                {
                    MainActivity.admin_status = 1;
                }
                Name = muser.getDisplayName();
                email_address = muser.getEmail();
                State = dropdown.getSelectedItem().toString();
                District = dropdown2.getSelectedItem().toString();

                   Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                   startActivity(intent);

            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {

    }
}