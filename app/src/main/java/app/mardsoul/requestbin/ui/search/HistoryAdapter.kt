package app.mardsoul.requestbin.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.mardsoul.requestbin.R
import app.mardsoul.requestbin.databinding.ItemHistoryRequestBinding
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyRequestList = listOf<RequestHistoryItem>()

    fun setHistoryList(historyList: List<RequestHistoryItem>) {
        historyRequestList = historyList
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyItem: RequestHistoryItem) {
            with(binding) {
                timeRequestTextView.text = historyItem.timeRequest.toString()
                dateRequestTextView.text = historyItem.timeRequest.toString()
                binNumberTextView.text = historyItem.binNumber.toString()
                if (historyItem.isRequestSuccess) {
                    isSuccessImageView.setImageResource(R.drawable.ic_baseline_done_24)
                    isSuccessTextView.setText(R.string.is_success_text)
                } else {
                    isSuccessImageView.setImageResource(R.drawable.ic_baseline_block_24)
                    isSuccessTextView.setText(R.string.is_error_text)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryRequestBinding.inflate(inflater, parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyRequestList[position])
    }

    override fun getItemCount(): Int = historyRequestList.size
}

