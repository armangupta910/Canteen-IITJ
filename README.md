<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<h1>Canteen IITJ</h1>

<p>Canteen IITJ is an Android application developed using Kotlin and XML, designed to meet the online food ordering needs of users. The app leverages Firebase for backend services and provides features for both customers and admins to enhance the overall food ordering experience.</p>

<h2>Features</h2>

<h3>Customer Features</h3>
<ul>
    <li><strong>Add to Cart</strong>: Users can browse the menu and add items to their cart.</li>
    <li><strong>Add to Favourites</strong>: Users can mark their favourite dishes for quick access.</li>
    <li><strong>Order Cart</strong>: Users can place orders for the items in their cart.</li>
    <li><strong>Order History</strong>: Users can view their past orders.</li>
    <li><strong>Profile Management</strong>: Users can manage and update their profile information.</li>
</ul>

<h3>Admin Features</h3>
<ul>
    <li><strong>Manage Item Availability</strong>: Admins can mark items as available or unavailable.</li>
    <li><strong>View Current Orders</strong>: Admins can see the orders that are currently being processed.</li>
    <li><strong>View Fulfilled Orders</strong>: Admins can view the history of fulfilled orders.</li>
</ul>

<h2>Getting Started</h2>

<h3>Prerequisites</h3>
<ul>
    <li>Android Studio 4.1 or later</li>
    <li>Kotlin 1.5 or later</li>
    <li>Firebase Account</li>
</ul>

<h3>Installation</h3>

<ol>
    <li><strong>Clone the Repository</strong>
        <pre><code>git clone https://github.com/armangupta910/Canteen-IITJ/
cd Canteen-IITJ</code></pre>
    </li>
    <li><strong>Open in Android Studio</strong>
        <ul>
            <li>Open Android Studio.</li>
            <li>Select "Open an existing Android Studio project".</li>
            <li>Navigate to the cloned repository and open it.</li>
        </ul>
    </li>
    <li><strong>Setup Firebase</strong>
        <ul>
            <li>Go to the <a href="https://console.firebase.google.com/" target="_blank">Firebase Console</a>.</li>
            <li>Create a new project or use an existing one.</li>
            <li>Add an Android app to your Firebase project.</li>
            <li>Register your app with the package name (e.g., <code>com.example.canteeniitj</code>).</li>
            <li>Download the <code>google-services.json</code> file and place it in the <code>app</code> directory of your project.</li>
            <li>Follow the instructions to add Firebase SDK to your project.</li>
        </ul>
    </li>
    <li><strong>Sync Project with Gradle Files</strong>
        <ul>
            <li>Ensure your project-level <code>build.gradle</code> and app-level <code>build.gradle</code> files include the necessary Firebase dependencies.</li>
            <li>Sync the project with Gradle files.</li>
        </ul>
    </li>
</ol>

<h3>Running the App</h3>
<ul>
    <li>Connect your Android device or use an emulator.</li>
    <li>Click on the "Run" button in Android Studio.</li>
</ul>

<h2>Usage</h2>

<h3>Customer Flow</h3>
<ol>
    <li><strong>Sign Up / Log In</strong>: Create an account or log in with your credentials.</li>
    <li><strong>Browse Menu</strong>: Explore the menu and view item details.</li>
    <li><strong>Add to Cart</strong>: Add desired items to your cart.</li>
    <li><strong>Place Order</strong>: Review your cart and place the order.</li>
    <li><strong>Manage Profile</strong>: Update your profile information as needed.</li>
    <li><strong>Order History</strong>: View your previous orders.</li>
</ol>

<h3>Admin Flow</h3>
<ol>
    <li><strong>Log In</strong>: Log in with admin credentials.</li>
    <li><strong>Manage Items</strong>: Mark items as available or unavailable.</li>
    <li><strong>Current Orders</strong>: View and manage orders that are currently being processed.</li>
    <li><strong>Fulfilled Orders</strong>: Review the history of fulfilled orders.</li>
</ol>

<h2>Contributing</h2>
<p>Contributions are welcome! Please follow these steps to contribute:</p>
<ol>
    <li>Fork the repository.</li>
    <li>Create a new branch (<code>git checkout -b feature-branch</code>).</li>
    <li>Make your changes and commit them (<code>git commit -m 'Add some feature'</code>).</li>
    <li>Push to the branch (<code>git push origin feature-branch</code>).</li>
    <li>Create a new Pull Request.</li>
</ol>

<h2>Acknowledgements</h2>
<ul>
    <li>Thanks to <a href="https://firebase.google.com/" target="_blank">Firebase</a> for providing the backend infrastructure.</li>
    <li>Special thanks to the open-source community for their valuable resources and contributions.</li>
</ul>

</body>
</html>
