package com.brandon.food.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brandon.food.databinding.FragmentMainRoundedBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MainRoundedBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMainRoundedBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainRoundedBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기에서 Bottom Sheet의 내용을 구성할 수 있다
        // 필요한 UI 컴포넌트를 바인딩하고 클릭리스너 등 설정

        val screenHeight = resources.displayMetrics.heightPixels
        val layoutParams = view.layoutParams
        layoutParams.height = (screenHeight * 0.3).toInt()
        view.layoutParams = layoutParams

    }
}