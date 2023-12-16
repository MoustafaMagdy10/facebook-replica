//package LoaderDeloader;
//
//import publications.Comment;
//import publications.Post;
//import publications.Reply;
//import publications.User;
//
//import java.io.*;
//import java.util.TreeSet;
//
//public class FileProcessing
//{
// public static void loadingData() throws IOException
// {
//     //Loading data of users
//
//     BufferedReader userReader = new BufferedReader(new FileReader("LoaderDeloader/UserData.txt"));
//
//     String data;
//
//     String userName;
//     String gender;
//     String email;
//     String password;
//     int birthDay;
//     int birthMonth;
//     int birthYear;
//     String phoneNumber;
//     TreeSet<Integer> posts = new TreeSet<>();
//
//         while ((data = userReader.readLine()) != null)
//         {
//             userName = data;
//             gender = userReader.readLine();
//             email = userReader.readLine();
//             password = userReader.readLine();
//             birthDay = Integer.parseInt(userReader.readLine());
//             birthMonth = Integer.parseInt(userReader.readLine());
//             birthYear = Integer.parseInt(userReader.readLine());
//             phoneNumber = userReader.readLine();
//             while ((data = userReader.readLine()) != null)
//             {
//                 posts.add(Integer.parseInt(data));
//             }
//
//             User.loadUser(userName,gender,email,password,birthDay,birthMonth,birthYear,phoneNumber,posts);
//         }
//     /////////////////////////////////
//     //Loading data of posts
//
//     BufferedReader postReader = new BufferedReader(new FileReader("LoaderDeloader/PostData.txt"));
//     int publisherId;
//     String content;
//     boolean isPublic;
//     TreeSet<Integer>likes = new TreeSet<>();
//     TreeSet<Integer> tags = new TreeSet<>();
//     TreeSet<Integer> comments = new TreeSet<>();
//
//     while ((data = postReader.readLine()) != null)
//     {
//         publisherId = Integer.parseInt(data);
//         content = postReader.readLine();
//         isPublic = Boolean.parseBoolean(postReader.readLine());
//
//         while ((data = postReader.readLine()) != null)
//         {
//             likes.add(Integer.parseInt(data));
//         }
//
//         while ((data = postReader.readLine()) != null)
//         {
//             tags.add(Integer.parseInt(data));
//         }
//
//         while ((data = postReader.readLine()) != null)
//         {
//             comments.add(Integer.parseInt(data));
//         }
//
//         User.loadPost(publisherId,content,isPublic,likes,tags,comments);
//     }
//     /////////////////////////////////
//     //Loading data of comments
//
//     BufferedReader commentReader = new BufferedReader(new FileReader("LoaderDeloader/CommentData.txt"));
//     int commentPublisherId;
//     String commentContent;
//     TreeSet<Integer> commentLikes = new TreeSet<>();
//     TreeSet<Integer> replies = new TreeSet<>();
//
//     while ((data = commentReader.readLine()) != null)
//     {
//         commentPublisherId = Integer.parseInt(data);
//         commentContent = commentReader.readLine();
//
//         while ((data = commentReader.readLine()) != null)
//         {
//             commentLikes.add(Integer.parseInt(data));
//         }
//
//         while ((data = commentReader.readLine()) != null)
//         {
//            replies.add(Integer.parseInt(data));
//         }
//
//         Post.loadComment(commentPublisherId,commentContent,commentLikes,replies);
//     }
//     /////////////////////////////////
//     //Loading data of replies
//
//     BufferedReader replyReader = new BufferedReader(new FileReader("LoaderDeloader/ReplyData.txt"));
//     int replyPublisherId;
//     String replyContent;
//     TreeSet<Integer> replyLikes = new TreeSet<>();
//
//     while ((data = replyReader.readLine()) != null)
//     {
//         replyPublisherId = Integer.parseInt(data);
//         replyContent = replyReader.readLine();
//
//         while ((data = replyReader.readLine()) != null)
//         {
//             replyLikes.add(Integer.parseInt(data));
//         }
//
//         Comment.loadReply(replyPublisherId,replyContent,replyLikes);
//     }
//
// }
//
//
// public static void exportData() throws IOException
// {
//     //Export data of user
//
//     BufferedWriter userWriter = new BufferedWriter(new FileWriter("LoaderDeloader/UserData.txt"));
//     User[] userArray = User.exportUsers();
//
//     for(User userData : userArray)
//     {
//         userWriter.write(userData.getUserName());
//         userWriter.newLine();
//         userWriter.write(userData.getGender());
//         userWriter.newLine();
//         userWriter.write(userData.getEmail());
//         userWriter.newLine();
//         userWriter.write(userData.getPassword());
//         userWriter.newLine();
//         userWriter.write(String.valueOf(userData.getBirthDay()));
//         userWriter.newLine();
//         userWriter.write(String.valueOf(userData.getBirthMonth()));
//         userWriter.newLine();
//         userWriter.write(String.valueOf(userData.getBirthYear()));
//         userWriter.newLine();
//         userWriter.write(userData.getPhoneNumber());
//         userWriter.newLine();
//
//         Post[] userPosts = userData.getPosts();
//
//         for (Post userPost : userPosts)
//         {
//             userWriter.write(String.valueOf(userPost.getId()));
//             userWriter.newLine();
//         }
//         userWriter.newLine();
//     }
//     userWriter.close();
//     ///////////////////////////
//     //Export data of posts
//
//     BufferedWriter postWriter = new BufferedWriter(new FileWriter("LoaderDeloader/PostData.txt"));
//     Post[] postArray = User.exportPosts();
//
//
//     for (Post postData : postArray)
//     {
//         postWriter.write(String.valueOf(postData.getPublisherId()));
//         postWriter.newLine();
//         postWriter.write(postData.getContent());
//         postWriter.newLine();
//         postWriter.write(String.valueOf(postData.isPublic()));
//         postWriter.newLine();
//
//         User[] postLikers = postData.getLikes();
//
//         for (User postLiker : postLikers)
//         {
//             postWriter.write(String.valueOf(postLiker.getId()));
//             postWriter.newLine();
//         }
//         postWriter.newLine();
//         //
//         User[] postTags = postData.getTags();
//
//         for (User postTag : postTags)
//         {
//             postWriter.write(String.valueOf(postTag.getId()));
//             postWriter.newLine();
//         }
//         postWriter.newLine();
//         //
//         Comment[] postComments = postData.getComments();
//
//         for (Comment postComment : postComments)
//         {
//             postWriter.write(String.valueOf(postComment.getId()));
//             postWriter.newLine();
//         }
//         postWriter.newLine();
//     }
//     postWriter.close();
//     ///////////////////////////
//     //Export data of comments
//
//     BufferedWriter commentWriter = new BufferedWriter(new FileWriter("LoaderDeloader/CommentData.txt"));
//     Comment[] commentArray = Post.exportComments();
//
//     for (Comment commentData : commentArray)
//     {
//         commentWriter.write(String.valueOf(commentData.getPublisherId()));
//         commentWriter.newLine();
//         commentWriter.write(commentData.getContent());
//         commentWriter.newLine();
//
//         User[] commentLikes = commentData.getLikes();
//
//         for (User commentsLike : commentLikes)
//         {
//             commentWriter.write(String.valueOf(commentsLike.getId()));
//             commentWriter.newLine();
//         }
//         commentWriter.newLine();
//         //
//         Reply[] commentReplies = commentData.getReplies();
//
//         for (Reply commentReply : commentReplies)
//         {
//             commentWriter.write(String.valueOf(commentReply.getId()));
//             commentWriter.newLine();
//         }
//         commentWriter.newLine();
//     }
//     commentWriter.close();
//     ///////////////////////////
//     //Export data of replies
//
//     BufferedWriter replyWriter = new BufferedWriter(new FileWriter("LoaderDeloader/ReplyData.txt"));
//     Reply[] replyArray = Comment.exportReply();
//
//     for (Reply replyData : replyArray)
//     {
//         replyWriter.write(String.valueOf(replyData.getPublisherId()));
//         replyWriter.newLine();
//         replyWriter.write(replyData.getContent());
//         replyWriter.newLine();
//
//         User[] replyLikers = replyData.getLikes();
//         for(User replyLiker : replyLikers)
//         {
//             replyWriter.write(String.valueOf(replyLiker.getId()));
//             replyWriter.newLine();
//         }
//         replyWriter.newLine();
//     }
//     replyWriter.close();
// }
//
//}
