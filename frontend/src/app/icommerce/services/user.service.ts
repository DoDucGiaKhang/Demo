import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
    private usersUrl = '/api/users'
    private registerUrl = '/api/users/register'

    constructor(private http: HttpClient) { }

    getAllUsers() {
        return this.http.get(this.usersUrl);
    }

    register(user: User) {
        return this.http.post(this.registerUrl, user);
    }

    delete(id: number) {
        return this.http.delete(this.usersUrl + `${id}`);
    }
}