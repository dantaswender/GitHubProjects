package br.com.githubprojects.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.githubprojects.R
import br.com.githubprojects.model.Item
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(
    private val listaRepositorios: MutableList<Item> = mutableListOf()
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lista_repositorios, parent, false)
        return MainViewHolder(inflate)
    }

    override fun getItemCount() = listaRepositorios.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindView(listaRepositorios[position])
    }

    fun atualiza(item: List<Item>) {
        listaRepositorios.addAll(item)
        notifyDataSetChanged()
    }

    class MainViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val txtNomeRepositorio: TextView by lazy { item.findViewById(R.id.tv_nome_repositorio) }
        private val txtForkCount: TextView by lazy { item.findViewById(R.id.tv_fork_count) }
        private val txtStars: TextView by lazy { item.findViewById(R.id.tv_stargazer_count) }
        private val imgProfile: CircleImageView by lazy { item.findViewById(R.id.img_profile) }
        private val userName: TextView by lazy { item.findViewById(R.id.tv_user_name) }

        fun bindView(item: Item) {

            txtNomeRepositorio.text = item.name
            txtForkCount.text = item.forksCount.toString()
            txtStars.text = item.stargazersCount.toString()
            Picasso.get().load(item.owner.avatarUrl).into(imgProfile)
            userName.text = item.owner.login
        }

    }
}