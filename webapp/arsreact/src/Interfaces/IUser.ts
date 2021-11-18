export interface IUser {
    id: number,
    username: string,
    password: string,
    firstname: string,
    lastname: string,
    roleid: number,
    email: string
}

export type AppState = {
    user: IUser
}