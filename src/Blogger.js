import React from 'react';

// Data for Books
export const books = [
    { id: 101, bname: 'Master React', price: 670 },
    { id: 102, bname: 'Deep Dive into Angular 11', price: 800 },
    { id: 103, bname: 'Mongo Essentials', price: 450 },
];

// 1. List Component for Books
const bookdet = (
    <ul>
        {books.map((book) => (
            <div key={book.id}>
                <h3>{book.bname}</h3>
                <h4>{book.price}</h4>
            </div>
        ))}
    </ul>
);

// 2. Mock Content for Blog and Course
const content = <div><p>React Learning</p><p>Stephen Biz</p><p>Welcome to learning React!</p></div>;
const coursedet = <div><p>Angular</p><p>4/5/2021</p><p>React</p><p>6/3/2021</p></div>;

// Main Blogger Component
export default function Blogger() {
    return (
        <div>
            <div className="st2">
                <h1>Book Details</h1>
                {bookdet}
            </div>
            <div className="v1">
                <h1>Blog Details</h1>
                {content}
            </div>
            <div className="mystyle1">
                <h1>Course Details</h1>
                {coursedet}
            </div>
        </div>
    );
}