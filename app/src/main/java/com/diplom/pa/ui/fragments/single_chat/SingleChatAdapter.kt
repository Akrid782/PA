package com.diplom.pa.ui.fragments.single_chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.diplom.pa.R
import com.diplom.pa.database.CURRENT_ID
import com.diplom.pa.models.CommonModel
import com.diplom.pa.utility.asTime
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mlistMessagesCache = emptyList<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chatBlockUserMessage: ConstraintLayout = view.chat_block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val chatBlockReceivingMessage: ConstraintLayout = view.chat_block_receiving_message
        val chatReceivingMessage: TextView = view.chat_receiving_message
        val chatReceivingMessageTime: TextView = view.chat_receiving_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mlistMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        Log.d("Erк1", mlistMessagesCache[position].from + " | " + CURRENT_ID)
        if (mlistMessagesCache[position].from == CURRENT_ID) {
            holder.chatBlockUserMessage.visibility = View.VISIBLE
            holder.chatBlockReceivingMessage.visibility = View.GONE
            holder.chatUserMessage.text = mlistMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mlistMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.chatBlockUserMessage.visibility = View.GONE
            holder.chatBlockReceivingMessage.visibility = View.VISIBLE
            holder.chatReceivingMessage.text = mlistMessagesCache[position].text
            holder.chatReceivingMessageTime.text =
                mlistMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    fun setList(list: List<CommonModel>) {
        mlistMessagesCache = list
        notifyDataSetChanged()
    }
}


