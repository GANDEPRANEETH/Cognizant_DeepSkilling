import React, { Component } from 'react';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: []
        };
    }

    // Fetch posts from API
    loadPosts() {
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => response.json())
            .then(data => this.setState({ posts: data }))
            .catch(error => console.error('Error fetching posts:', error));
    }

    // Lifecycle hook to trigger data load
    componentDidMount() {
        this.loadPosts();
    }

    // Error handling lifecycle hook
    componentDidCatch(error, info) {
        alert("An error occurred: " + error.message);
    }

    render() {
        return (
            <div>
                <h1>Blog Posts</h1>
                {this.state.posts.map(post => (
                    <div key={post.id}>
                        <h3>{post.title}</h3>
                        <p>{post.body}</p>
                    </div>
                ))}
            </div>
        );
    }
}

export default Posts;