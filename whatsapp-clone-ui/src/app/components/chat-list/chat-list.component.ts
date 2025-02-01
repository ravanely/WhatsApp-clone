import {Component, input, InputSignal} from '@angular/core';
import {ChatResponse} from '../../services/models/chat-response';
import {DatePipe} from '@angular/common';
import {UserResponse} from '../../services/models/user-response';
import {UserService} from '../../services/services/user.service';

@Component({
  selector: 'app-chat-list',
  imports: [
    DatePipe
  ],
  templateUrl: './chat-list.component.html',
  styleUrl: './chat-list.component.scss'
})
export class ChatListComponent {
  chats: InputSignal<ChatResponse[]> = input<ChatResponse[]>([])
  searchNewContact = false
  contacts: Array<UserResponse> = [];

  constructor(private userService: UserService) {
  }

  searchContact() {
    this.userService.getAllUsersExceptSelf().subscribe({
      next: (users) => {
        this.contacts = users;
        this.searchNewContact = true;
      }
    })
  }

  chatClicked(chat: ChatResponse) {

  }

  wrapMessage(lastMessage: string | undefined) {
    if (lastMessage && lastMessage.length <= 20) {
      return lastMessage
    }
    return lastMessage?.substring(0, 17) + '...';
  }

  selectContact(contact: UserResponse) {

  }
}
