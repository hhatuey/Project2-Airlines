export interface IUser {
    userId: number,
    username: string,
    password: string,
    firstName: string,
    lastName: string,
    roleId: number,
    email: string   
}

export type AppState = {
    user: IUser
}