package com.school.autaxion


import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.school.autaxion.databinding.FragmentDashboardBinding
import com.school.autaxion.models.CarAdapter
import com.school.autaxion.models.CarModel
import org.json.JSONException

<<<<<<< HEAD

class FragmentCarModelActivity : Fragment() {

=======
class FragmentCarModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_car_model)
    }
>>>>>>> parent of ae04353 (Ophalen data met volley)
}