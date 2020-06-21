package com.diplom.pa.ui.fragments.single_chat

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diplom.pa.R
import com.diplom.pa.database.CURRENT_ID
import com.diplom.pa.models.CommonModel
import com.diplom.pa.utility.TYPE_MESSAGE_IMAGE
import com.diplom.pa.utility.TYPE_MESSAGE_TEXT
import com.diplom.pa.utility.asTime
import com.diplom.pa.utility.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()
    private lateinit var mDiffResult: DiffUtil.DiffResult

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Текст
        val chatBlockUserMessage: ConstraintLayout = view.chat_block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user_message_time

        val chatBlockReceivingMessage: ConstraintLayout = view.chat_block_receiving_message
        val chatReceivingMessage: TextView = view.chat_receiving_message
        val chatReceivingMessageTime: TextView = view.chat_receiving_message_time

        //Картинки
        val chatBlockUserImage: ConstraintLayout = view.chat_block_user_image
        val chatUserImage: ImageView = view.chat_user_image
        val chatUserImageTime: TextView = view.chat_user_image_time

        val chatBlockReceivingImage: ConstraintLayout = view.chat_block_receiving_image
        val chatReceivingImage: ImageView = view.chat_receiving_image
        val chatReceivingImageTime: TextView = view.chat_receiving_image_time


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun getItemCount(): Int = mListMessagesCache.size

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessagesCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)
        }

    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.chatBlockUserMessage.visibility = View.GONE
        holder.chatBlockReceivingMessage.visibility = View.GONE

        if (mListMessagesCache[position].from == CURRENT_ID) {
            holder.chatBlockUserImage.visibility = View.VISIBLE
            holder.chatBlockReceivingImage.visibility = View.GONE
            holder.chatUserImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatUserImageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.chatBlockUserImage.visibility = View.GONE
            holder.chatBlockReceivingImage.visibility = View.VISIBLE
            holder.chatReceivingImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatReceivingImageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.chatBlockUserImage.visibility = View.GONE
        holder.chatBlockReceivingImage.visibility = View.GONE

        if (mListMessagesCache[position].from == CURRENT_ID) {
            holder.chatBlockUserMessage.visibility = View.VISIBLE
            holder.chatBlockReceivingMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.chatBlockUserMessage.visibility = View.GONE
            holder.chatBlockReceivingMessage.visibility = View.VISIBLE
            holder.chatReceivingMessage.text = mListMessagesCache[position].text
            holder.chatReceivingMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    fun addItemToBottom(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            notifyItemInserted(mListMessagesCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(item: CommonModel, onSuccess: () -> Unit) {
        if (!mListMessagesCache.contains(item)) {
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}


