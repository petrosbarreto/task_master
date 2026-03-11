package com.example.taskmaster

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter do ViewPager2 para as abas Pendentes (0) e Concluídas (1).
 * Aula 07 — TabLayout + ViewPager2
 */
class AbasViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> PendentesFragment()
        else -> ConcluidasFragment()
    }
}

