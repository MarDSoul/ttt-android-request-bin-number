package app.mardsoul.requestbin.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.mardsoul.requestbin.R
import app.mardsoul.requestbin.databinding.ItemHistoryRequestBinding
import app.mardsoul.requestbin.domain.entities.RequestHistoryItem
import app.mardsoul.requestbin.utils.getDate
import app.mardsoul.requestbin.utils.getTime

class HistoryAdapter(private val onClickItemHistory: OnClickItemHistory) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyRequestList = listOf<RequestHistoryItem>()

    fun setHistoryList(historyList: List<RequestHistoryItem>) {
        historyRequestList = historyList
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyItem: RequestHistoryItem) {
            with(binding) {
                root.tag = historyItem
                timeRequestTextView.text = historyItem.timeRequest.getTime()
                dateRequestTextView.text = historyItem.timeRequest.getDate()
                binNumberTextView.text = historyItem.binNumber
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
        val binding = ItemHistoryRequestBinding.inflate(inflater, parent, false).apply {
            root.setOnClickListener(onClickItemHistory)
        }
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyRequestList[position])
    }

    override fun getItemCount(): Int = historyRequestList.size
}

