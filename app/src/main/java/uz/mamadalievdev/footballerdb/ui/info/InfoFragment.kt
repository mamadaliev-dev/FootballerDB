package uz.mamadalievdev.footballerdb.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import uz.mamadalievdev.footballerdb.R
import uz.mamadalievdev.footballerdb.databinding.FragmentInfoBinding
import uz.mamadalievdev.footballerdb.ui.BaseFragment

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {
    override fun onViewCreate() {
        binding.apply {
            name.text = requireArguments().getString("PLAYER", "Error")
            biography.text = requireArguments().getString("BIOGRAPHY", "Error")

            Glide.with(binding.root.context)
                .load(requireArguments().getString("IMAGE", "Error"))
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(binding.cutout)
        }
    }
}