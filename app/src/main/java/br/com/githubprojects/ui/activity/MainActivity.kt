package br.com.githubprojects.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.githubprojects.R
import br.com.githubprojects.databinding.ActivityMainBinding
import br.com.githubprojects.ui.adapter.MainAdapter
import br.com.githubprojects.ui.viewmodel.MainViewModel
import br.com.githubprojects.ui.viewmodel.state.MainState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val adapter by lazy {
        MainAdapter()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutManager: LinearLayoutManager

    private var isLoading = false

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        criarObserver()
        configurarRecyclerView()
        configurarScroll()

    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.rv_lista_repositorios)
        progressBar = findViewById(R.id.progressBar)

    }

    private fun criarObserver() {
        viewModel.viewState.observe(this, Observer { viewState ->
            viewState?.let {
                when (it) {
                    is MainState.GetListaRepositorios -> {
                        adapter.atualiza(it.lista)
                        isLoading = false
                        progressBar.visibility = GONE
                    }
                }
            }
        })

        viewModel.buscarListaRepositorios()
    }

    private fun configurarRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun configurarScroll() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = layoutManager.childCount
                val pastItemVisible = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoading) {
                    if ((visibleItemCount + pastItemVisible) >= total) {
                        mudarLoading()
                        Log.i("pagina", "buscar pagina")
                        viewModel.buscarListaRepositorios()
                    }
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun mudarLoading(){
        if(isLoading){
            isLoading = false
            progressBar.visibility = GONE
        }else{
            isLoading = true
            progressBar.visibility = VISIBLE
        }
    }

}