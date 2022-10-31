import { FC, FormEventHandler } from "react"

export const CreateDeckForm: FC = () => {
    const onSubmit: FormEventHandler<HTMLFormElement> = (event) => {
        event.preventDefault()
    }
    return (
        <form onSubmit={onSubmit} className='bg-white border-4 p-4 w-80 shadow-md'>
            <label htmlFor="deckName" className='block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300'>
                Name
                <input type="text" id="deckName" name="deckName" placeholder="deckname" className='mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm  focus:ring-blue-500 focus:border-blue-500 block p-2.5 w-full dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 required' />
            </label>
            <input type="submit" value="Create" className='text-white bg-sky-500 w-32 py-2 mt-4' />
        </form>
    )
}